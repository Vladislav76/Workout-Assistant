package com.vladislavmyasnikov.feature_exercise_book_impl.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vladislavmyasnikov.feature_exercise_book_impl.data.db.converters.IntListConverter
import com.vladislavmyasnikov.feature_exercise_book_impl.data.db.dao.ExerciseBookDao
import com.vladislavmyasnikov.feature_exercise_book_impl.data.db.entities.FullExerciseInfo

@Database(entities = [FullExerciseInfo::class], version = 1, exportSchema = false)
@TypeConverters(value = [IntListConverter::class])
abstract class LocalDatabase : RoomDatabase() {

    abstract fun exerciseBookDao(): ExerciseBookDao
}