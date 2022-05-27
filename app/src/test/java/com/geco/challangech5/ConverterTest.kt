package com.geco.challangech5

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class ConverterTest : TestCase() {

    var imageEncoded = "Testing"

    @Test
    fun testToBitmap(): Boolean {
        if (imageEncoded.isNotEmpty()){
            return true
        }
        return false
    }

}