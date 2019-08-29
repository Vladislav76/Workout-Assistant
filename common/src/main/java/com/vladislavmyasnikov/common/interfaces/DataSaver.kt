package com.vladislavmyasnikov.common.interfaces

import androidx.room.RoomDatabase

interface DataSaver {

    fun saveData(db: RoomDatabase)
}