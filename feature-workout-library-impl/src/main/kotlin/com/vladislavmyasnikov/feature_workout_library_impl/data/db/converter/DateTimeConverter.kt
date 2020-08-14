package com.vladislavmyasnikov.feature_workout_library_impl.data.db.converter

import androidx.room.TypeConverter
import com.vladislavmyasnikov.common.models.TimePoint
import java.util.*

class DateTimeConverter {

    @TypeConverter
    fun timestampToDate(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun timestampToTime(value: Long?): TimePoint? = value?.let { TimePoint(value) }

    @TypeConverter
    fun timeToTimestamp(time: TimePoint?): Long? = time?.toMilliseconds()
}