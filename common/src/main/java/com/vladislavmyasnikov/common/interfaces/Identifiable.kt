package com.vladislavmyasnikov.common.interfaces

interface Identifiable<T : Identifiable<T>> {

    val id: Long
    fun isIdentical(another: T): Boolean = another.id == id
}

// TODO: try use it
interface NewIdentifiable {

    val id: Long
    fun isIdentical(another: NewIdentifiable) = (this.javaClass == another.javaClass) && (id == another.id)
}