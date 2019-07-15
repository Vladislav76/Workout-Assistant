package com.vladislavmyasnikov.core_utils.utils.utils

import java.text.SimpleDateFormat
import java.util.*

object DayMonthYearFormat : SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

object HourMinuteFormat : SimpleDateFormat("HH:mm", Locale.getDefault()) {

    init {
        timeZone = TimeZone.getTimeZone("GTM")
    }
}