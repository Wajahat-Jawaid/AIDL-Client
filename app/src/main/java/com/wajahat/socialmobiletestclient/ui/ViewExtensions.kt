package com.wajahat.socialmobiletestclient.ui

import android.os.SystemClock
import android.view.View
import android.widget.EditText

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
/** Apply the throttling effect to any view
 * @param debounceTime default debounce time of 400 ms
 * @param action method to be invoked
 * */
fun View.throttlingClickListener(debounceTime: Long = 400L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun EditText.toTrimmedString(): String {
    return this.text.toString().trim()
}