package com.vladislavmyasnikov.common.interfaces

interface Identifiable<T> {

    val id: Long
    fun isIdentical(another: T): Boolean
}