package com.vladislav.workoutassistant.data.db.converter

import androidx.room.TypeConverter

class ListConverter {

    @TypeConverter
    fun stringToIntList(data: String): List<Int> = if (data == "") listOf() else data.split(",").map { it.toInt() }

    @TypeConverter
    fun intListToString(list: List<Int>): String = list.joinToString(",")
}