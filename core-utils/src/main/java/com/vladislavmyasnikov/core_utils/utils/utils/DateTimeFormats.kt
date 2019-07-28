package com.vladislavmyasnikov.core_utils.utils.utils

import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

object DayMonthYearFormat : SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

object HourMinuteFormat {
    private val timeFormat = "[0-9]*:[0-9]*".toRegex()

    fun format(time: Time): String {
        return timeFormat.find(time.toString(), 0)?.value ?: "Not formatted"
    }
}