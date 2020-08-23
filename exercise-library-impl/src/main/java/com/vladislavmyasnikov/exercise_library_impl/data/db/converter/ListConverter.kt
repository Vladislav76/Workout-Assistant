package com.vladislavmyasnikov.exercise_library_impl.data.db.converter

import androidx.room.TypeConverter

class ListConverter {

    @TypeConverter
    fun stringToIntList(data: String): List<Int> = if (data == "") emptyList() else data.split(",").map { it.toInt() }

    @TypeConverter
    fun intListToString(list: List<Int>): String = list.joinToString(",")

    @TypeConverter
    fun stringToStringList(data: String): List<String> = if (data == "") emptyList() else data.split(",")

    @TypeConverter
    fun stringListToString(list: List<String>): String = list.joinToString(",")
}