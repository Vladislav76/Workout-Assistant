package com.vladislavmyasnikov.feature_workout_library_impl.data.db.converters

import androidx.room.TypeConverter

class ListConverter {

    @TypeConverter
    fun stringToLongList(data: String): List<Long> = if (data == "") emptyList() else data.split(",").map(String::toLong)

    @TypeConverter
    fun longListToString(list: List<Long>): String = list.joinToString(",")

    @TypeConverter
    fun stringToListOfIntList(data: String): List<List<Int>> = if (data == "") emptyList() else data.split(";").map { subList -> subList.split(",").map(String::toInt) }

    @TypeConverter
    fun listOfIntListToString(list: List<List<Int>>): String = list.joinToString(separator = ";", transform = { subList -> subList.joinToString(separator = ",") })
}