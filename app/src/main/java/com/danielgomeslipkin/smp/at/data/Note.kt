package com.danielgomeslipkin.smp.at.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.lang.Exception
import java.util.*

class Note (
    val content : String,
    val title : String,
    val date : String,
    val imgUri : String,
    val email : String,
    var filepath : String = "",
        ) {



    override fun toString(): String {
        val str = "$title$sep$date$sep$imgUri$sep$email$sep$content"
        return str
    }

    fun getBitmap() : Bitmap? {
        try {
            val imgstr = Base64.getDecoder().decode(imgUri)
            return BitmapFactory.decodeByteArray(imgstr, 0, imgstr.size)
        } catch(e : Exception) {
            Log.d("bitdeath", imgUri)
            return null
        }
    }

    fun getByteArray() : ByteArray {
        try {
            return Base64.getDecoder().decode(imgUri)

        } catch(e : Exception) {
            Log.d("bitdeath", imgUri)
            return ByteArray(1)
        }
    }


    companion object {
        val sep = "<bruh>"

        fun StrToNote(str:String) : Note? {
            val sp = str.split(sep)
            if (sp.size == 5)
                return Note( sp[4], sp[0], sp[1], sp[2], sp[3] )
            else
                return null
        }
    }

}

