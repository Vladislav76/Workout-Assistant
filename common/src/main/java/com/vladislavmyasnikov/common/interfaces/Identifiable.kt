package com.vladislavmyasnikov.common.interfaces

interface Identifiable<T> {

    fun isIdentical(another: T): Boolean
}