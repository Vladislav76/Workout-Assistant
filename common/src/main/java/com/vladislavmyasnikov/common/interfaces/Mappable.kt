package com.vladislavmyasnikov.common.interfaces

fun interface Mappable<T,V> {

    fun map(value: T): V
}