package com.vladislavmyasnikov.core_utils.utils.utils

import android.util.Log

object Logger {

    fun debug(who: CharSequence, message: CharSequence) {
        Log.d(who.toString(), message.toString())
    }
}