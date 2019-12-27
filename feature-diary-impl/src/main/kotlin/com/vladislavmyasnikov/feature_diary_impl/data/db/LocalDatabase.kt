package com.vladislavmyasnikov.feature_diary_impl.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vladislavmyasnikov.feature_diary_impl.data.db.converters.DateTimeConverter
import com.vladislavmyasnikov.feature_diary_impl.data.db.dao.DiaryDao
import com.vladislavmyasnikov.feature_diary_impl.data.db.entities.FullDiaryEntry

@Database(entities = [FullDiaryEntry::class], version = 1, exportSchema = false)
@TypeConverters(value = [DateTimeConverter::class])
abstract class LocalDatabase : RoomDatabase() {

    abstract fun diaryDao(): DiaryDao
}


