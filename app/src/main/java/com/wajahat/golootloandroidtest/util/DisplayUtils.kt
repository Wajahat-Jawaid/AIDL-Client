package com.wajahat.golootloandroidtest.util

import android.content.res.Resources

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
object DisplayUtils {

    fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    fun getScreenHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }
}