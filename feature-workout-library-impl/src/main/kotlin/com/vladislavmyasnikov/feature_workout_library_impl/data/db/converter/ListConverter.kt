package com.vladislavmyasnikov.feature_workout_library_impl.data.db.converter

import androidx.room.TypeConverter

class ListConverter {

    @TypeConverter
    fun stringToLongList(data: String): List<Long> = if (data == "") emptyList() else data.split(",").map(String::toLong)

    @TypeConverter
    fun stringToIntList(data: String): List<Int> = if (data == "") emptyList() else data.split(",").map(String::toInt)

    @TypeConverter
    fun stringToFloatList(data: String): List<Float> = if (data == "") emptyList() else data.split(",").map(String::toFloat)

    @TypeConverter
    fun listToString(list: List<Any>): String = list.joinToString(",")

//    @TypeConverter
//    fun stringToListOfIntList(data: String): List<List<Int>> = if (data == "") emptyList() else data.split(";").map { subList -> subList.split(",").map(String::toInt) }
//
//    @TypeConverter
//    fun listOfIntListToString(list: List<List<Int>>): String = list.joinToString(separator = ";", transform = { subList -> subList.joinToString(separator = ",") })
}