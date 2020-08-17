package com.vladislavmyasnikov.exercise_library_impl.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vladislavmyasnikov.exercise_library_impl.data.db.converters.ListConverter
import com.vladislavmyasnikov.exercise_library_impl.data.db.dao.ExerciseLibraryDao
import com.vladislavmyasnikov.exercise_library_impl.data.db.entities.FullExerciseInfo

@Database(entities = [FullExerciseInfo::class], version = 1, exportSchema = false)
@TypeConverters(value = [ListConverter::class])
abstract class LocalDatabase : RoomDatabase() {

    abstract fun exerciseLibraryDao(): ExerciseLibraryDao
}