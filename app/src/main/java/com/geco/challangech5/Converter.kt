package com.geco.challangech5

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

class Converter {

    fun fromBitmap(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        val baos = outputStream.toByteArray()
        val imageEncoded: String = Base64.encodeToString(baos, Base64.DEFAULT)
        return imageEncoded
    }


    fun toBitmap(imageEncoded: String?): Bitmap{
        val decodeByte = Base64.decode(imageEncoded, 0)
        return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.size)
    }

}