package com.vladislavmyasnikov.core_utils.utils.interfaces

import androidx.room.RoomDatabase

interface DataSaver {

    fun saveData(db: RoomDatabase)
}