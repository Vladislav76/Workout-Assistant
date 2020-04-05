package com.vladislavmyasnikov.common.utils

import android.util.Log
import kotlin.reflect.KClass

object Logger {

    fun debug(who: KClass<*>, message: CharSequence) {
        val label = ClassLabelLibrary.getLabel(who)
        Log.d(label, message.toString())
    }

    fun debug(who: CharSequence, message: CharSequence) {
        Log.d(who.toString(), message.toString())
    }
}