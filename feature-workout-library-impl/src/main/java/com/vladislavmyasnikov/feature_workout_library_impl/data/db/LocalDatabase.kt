package com.vladislavmyasnikov.feature_workout_library_impl.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.converters.ListConverter
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.dao.WorkoutLibraryDao
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.SetInfo
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutInfo

@Database(entities = [SetInfo::class, WorkoutInfo::class], version = 1, exportSchema = false)
@TypeConverters(value = [ListConverter::class])
abstract class LocalDatabase : RoomDatabase() {

    abstract fun workoutLibraryDao(): WorkoutLibraryDao
}