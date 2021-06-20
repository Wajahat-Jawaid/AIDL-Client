package com.wajahat.golootloandroidtest.ui

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
suspend fun Bitmap.save(context: Context) {
    try {
        val root = context.getExternalFilesDir(null)?.absolutePath
        var myDir = File("$root/golootloAndroidTest")
        // Check if this folder exists in File Manager Android/data/data/com.wajahat.golootloandroidtest/
        if (!myDir.exists()) {
            myDir.mkdirs()
        }
        val name = "golootlo_${System.currentTimeMillis()}.jpg"
        myDir = File(myDir, name)
        // switch to IO thread to perform writing operation
        withContext(Dispatchers.IO) {
            val out = FileOutputStream(myDir)
            this@save.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
        }
        // switch to main thread and show the confirmation toast
        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Image saved successfully!", Toast.LENGTH_LONG).show()
        }
    } catch (e: Exception) {
    }
}