package com.vladislav.workoutassistant.data.db.converter

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object StringConverter {

    fun dateToString(date: Date?): String? = if (date == null) null else DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault()).format(date)

    fun timeToString(time: Date?): String? = if (time == null) null else DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault()).format(time)

    fun durationToString(duration: Date?): String? {
        val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getTimeZone("GTM")
        return if (duration == null) null else simpleDateFormat.format(duration)
    }
}
