package com.vladislavmyasnikov.feature_diary_impl.data.db.converters

import androidx.room.TypeConverter
import com.vladislavmyasnikov.core_components.models.TimePoint
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