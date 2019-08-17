package com.vladislavmyasnikov.feature_exercise_book_impl.data.db.converters

import androidx.room.TypeConverter

class IntListConverter {

    @TypeConverter
    fun stringToIntList(data: String): List<Int> = if (data == "") listOf() else data.split(",").map { it.toInt() }

    @TypeConverter
    fun intListToString(list: List<Int>): String = list.joinToString(",")
}