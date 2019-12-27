package com.vladislavmyasnikov.common.models

class SyncObject<T>(private val init: () -> T) {

    @Volatile
    private var value: T? = null

    fun getValue(): T {
        return value ?: synchronized(this) {
            value ?: init().also { value = it }
        }
    }

    fun resetValue() {
        value = null
    }
}