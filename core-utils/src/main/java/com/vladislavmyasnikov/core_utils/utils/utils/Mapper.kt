package com.vladislavmyasnikov.core_utils.utils.utils

abstract class Mapper<T,V> {

    abstract fun map(value: T): V

    fun map(values: List<T>): List<V> = values.map(::map)
}