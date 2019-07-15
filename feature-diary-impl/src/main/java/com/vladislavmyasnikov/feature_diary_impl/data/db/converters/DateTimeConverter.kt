package com.vladislavmyasnikov.feature_diary_impl.data.db.converters

import androidx.room.TypeConverter
import java.sql.Time
import java.util.*

class DateTimeConverter {

    @TypeConverter
    fun timestampToDate(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun timestampToTime(value: Long?): Time? = value?.let { Time(it) }

    @TypeConverter
    fun timeToTimestamp(time: Time?): Long? = time?.time
}