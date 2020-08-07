package com.vladislavmyasnikov.common.utils

import com.vladislavmyasnikov.common.models.TimePoint
import java.util.*

fun getCurrentTimePoint(): TimePoint {
    val calendar = Calendar.getInstance().apply { time = Date() }
    val hours = calendar.get(Calendar.HOUR_OF_DAY)
    val minutes = calendar.get(Calendar.MINUTE)
    return TimePoint(hours.toLong(), minutes.toLong())
}