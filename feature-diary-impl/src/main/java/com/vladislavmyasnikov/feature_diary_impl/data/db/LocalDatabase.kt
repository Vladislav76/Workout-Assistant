package com.vladislavmyasnikov.feature_diary_impl.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DiaryEntry::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun diaryDao(): DiaryDao
}


