package com.vladislavmyasnikov.exercise_library_impl.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vladislavmyasnikov.exercise_library_impl.data.db.converter.ListConverter
import com.vladislavmyasnikov.exercise_library_impl.data.db.dao.ExerciseLibraryDao
import com.vladislavmyasnikov.exercise_library_impl.data.db.entity.ExerciseEntity

@Database(entities = [ExerciseEntity::class], version = 1, exportSchema = false)
@TypeConverters(value = [ListConverter::class])
abstract class LocalDatabase : RoomDatabase() {

    abstract fun exerciseLibraryDao(): ExerciseLibraryDao
}