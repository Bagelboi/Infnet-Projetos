package com.danielgomeslipkin.smp.at

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.danielgomeslipkin.smp.at.data.Utils
import com.danielgomeslipkin.smp.at.databinding.ActivityMainBinding
import com.google.android.gms.ads.MobileAds
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private var perms = listOf<String>(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
    )
    private var permsGranted = false
    private var permsCode = 420
    private lateinit var auth: FirebaseAuth;
    private var isPro = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        isPro = getPremium()

        if (!isPro)
            MobileAds.initialize(this) {}

        getPermissions(false)

        if (permsGranted) {
            nextActivity()
        }

        binding.btnPermissions.setOnClickListener {
            if (permsGranted) {
                nextActivity()
            } else {
                getPermissions(true)
            }
        }

        setContentView(view)


    }

    fun getPremium() : Boolean {
        return this.getPreferences(Context.MODE_PRIVATE).getBoolean(getString(R.string.premium_key), false)
    }

    fun nextActivity() {
        startActivity( Intent( this, LoginActivity::class.java ) )
        this.finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permsCode) {
            for (i in grantResults) {
                if (i != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(baseContext, "Voce precisa conceder as permissões necessarias!", Toast.LENGTH_SHORT).show()
                    permsGranted = false
                    return
                }
            }
            permsGranted = true
            binding.btnPermissions.performClick()

        }
    }

    private fun getPermissions(request : Boolean) {
        val perms2Get = mutableListOf<String>()
        for (perm in perms) {
            if (ActivityCompat.checkSelfPermission(baseContext, perm) != PackageManager.PERMISSION_GRANTED) {
                perms2Get.add(perm)
            }
        }
        if (perms2Get.isNotEmpty() && request == true) {
            ActivityCompat.requestPermissions(this, perms2Get.toTypedArray(), permsCode)
        } else if (perms2Get.isEmpty()) {
            permsGranted = true
        }
    }


}