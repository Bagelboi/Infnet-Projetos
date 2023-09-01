package com.danielgomeslipkin.smp.at.data

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.math.absoluteValue

object Utils {

    class Locationer(val act: Activity, val view : View, val requestCode : Int, val showSnack : Boolean = true) {
        var lat = 0.0
        var long = 0.0
        private var locationManager = act.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        private var locationListener = object : LocationListener {
            override fun onLocationChanged(p0: Location) {
                lat = p0.latitude
                long = p0.longitude
            }
        }
        val perms = arrayOf<String>(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        init {
            initialize()
        }

        fun initialize() {
            for (perm in perms) {
                val permState = act.checkSelfPermission(perm)
                if (permState != PackageManager.PERMISSION_GRANTED) {
                    if (showSnack) {
                        Snackbar.make(view,  "Seu GPS sera habilitado para salvar notas com uma localização especifica", Snackbar.LENGTH_LONG)
                            .setAction("Ok") {
                                act.requestPermissions(perms, requestCode)
                            }.show()
                    }
                    return
                }
            }

            //has perms
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                if (showSnack)
                    Toast.makeText(act.baseContext, "Habilite o seu GPS!", Toast.LENGTH_SHORT).show()
                return
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 100f, locationListener)
        }

        fun getAddress() : String {
            var addressStr = ""
            if (locationManager.isLocationEnabled) {
                if (Geocoder.isPresent()) {
                    val addressList =  Geocoder(act.baseContext).getFromLocation(lat, long, 5)
                    if (addressList != null && addressList.isNotEmpty()) {
                        val address = addressList.last()
                        addressStr = concatPrefix("", address.countryName) +
                                concatPrefix(", ", address.adminArea) +
                                concatPrefix(", ", address.subAdminArea) +
                                concatPrefix(", ", address.thoroughfare)
                    }
                }
            }
            Log.d("locData", "$lat, $long")
            return addressStr
        }

        fun stopListener() {
            locationManager.removeUpdates(locationListener)
        }


    }

    fun concatPrefix(prefix:String, obj:Any?) : String {
        if (obj != null) {
            return prefix + obj.toString()
        }
        return ""
    }

    fun localDate2Date(date : LocalDate) : Date {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant())
    }

    fun date2LocalDate(date: Date) : LocalDate {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    }

    fun makeViewListVisibility(views:List<View>, visible : Boolean) {
        for (view in views) {
            if (visible)
                view.visibility = View.VISIBLE
            else
                view.visibility = View.INVISIBLE
        }
    }

    fun doubleMapAddValues(mapvals:List<Double>) : Double {
        var sum = 0.0

        for (num in mapvals) {
            sum = sum + num
        }

        return sum
    }

    fun dateDoubleMapGetRanged(viceUses:HashMap<Date, Double>, refDate : LocalDate, chrono : ChronoUnit) : HashMap<Date, Double> {
        val trimmedMap = hashMapOf<Date, Double>()

        for (date in viceUses.keys.sorted()) {
            Log.d("uservicedatatest", "Loop : ${refDate.until( Utils.date2LocalDate(date), ChronoUnit.DAYS ).absoluteValue} days")
            if ( refDate.until( Utils.date2LocalDate(date), ChronoUnit.DAYS ).absoluteValue <= chrono.duration.toDays() ) {
                val dateUses = viceUses[date]
                if (dateUses is Double)
                    trimmedMap[date] = dateUses
            } else
                break
        }

        Log.d("uservicedatatest", "${trimmedMap.size} during ${chrono.name}")

        return trimmedMap

    }

    fun upperCaseFirstLetter(str:String) : String {
        if (str.length > 1) {
            return str[0].uppercase() + str.substring(1)
        }
        return str.uppercase()
    }



}