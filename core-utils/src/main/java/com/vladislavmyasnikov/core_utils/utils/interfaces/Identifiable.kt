package com.vladislavmyasnikov.core_utils.utils.interfaces

interface Identifiable<T> {

    fun isIdentical(another: T): Boolean
}