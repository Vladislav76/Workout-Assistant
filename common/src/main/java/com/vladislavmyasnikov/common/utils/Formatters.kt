package com.vladislavmyasnikov.common.utils

import com.vladislavmyasnikov.common.models.TimePoint
import java.text.SimpleDateFormat
import java.util.*

enum class TimePointFormatType {

    HOUR_MINUTE {
        override fun format(time: TimePoint): String = String.format("%02d:%02d", time.hours, time.minutes)
    },

    HOUR_MINUTE_SECOND {
        override fun format(time: TimePoint): String = String.format("%02d:%02d:%02d", time.hours, time.minutes, time.seconds)
    },

    DURATION {
        override fun format(time: TimePoint): String {
            val items = mutableListOf<String>()
            if (time.hours > 0) items.add(String.format("%d h", time.hours))
            if (time.minutes > 0) items.add(String.format("%d min", time.minutes))
            if (time.seconds > 0) items.add(String.format("%d sec", time.seconds))
            return items.joinToString()
        }
    };

    abstract fun format(time: TimePoint): String

    fun formatInterval(startTime: TimePoint, endTime: TimePoint): String = "${format(startTime)} ― ${format(endTime)}"
}

enum class DateFormatType {

    DAY_MONTH_YEAR {
        private val dateFormat = SimpleDateFormat.getDateInstance() // or SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        override fun format(date: Date): String = dateFormat.format(date)
    };

    abstract fun format(date: Date): String
}

// TODO: unused function
//fun convertDpToPixels(dp: Float, context: Context): Int {
//    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
//}