package com.vladislavmyasnikov.core_components.components

import com.vladislavmyasnikov.core_components.models.TimePoint
import java.text.SimpleDateFormat
import java.util.*

object TimePointFormatter {
    const val HOUR_MINUTE_FORMAT = 1
    const val HOUR_MINUTE_SECOND_FORMAT = 2

    fun format(time: TimePoint, type: Int): String {
        return when(type) {
            HOUR_MINUTE_FORMAT -> String.format("%02d:%02d", time.hours, time.minutes)
            HOUR_MINUTE_SECOND_FORMAT -> String.format("%02d:%02d:%02d", time.hours, time.minutes, time.seconds)
            else -> ""
        }
    }

    fun formatInterval(startTime: TimePoint, endTime: TimePoint, type: Int): String {
        return "${format(startTime, type)} ― ${format(endTime, type)}"
    }
}

object DateFormatter {
    const val DAY_MONTH_YEAR_FORMAT = 1

    private var dayMonthYearFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    fun format(date: Date, type: Int): String {
        return when(type) {
            DAY_MONTH_YEAR_FORMAT -> dayMonthYearFormat.format(date)
            else -> ""
        }
    }
}