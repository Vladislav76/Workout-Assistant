package com.vladislav.workoutassistant.data.db.converter

import androidx.room.TypeConverter
import java.sql.Time
import java.util.*

class DateTimeConverter {

    @TypeConverter
    fun timestampToDate(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun timestampToTime(value: Long?): Time? = if (value == null) null else Time(value)

    @TypeConverter
    fun timeToTimestamp(time: Time?): Long? = time?.time
}