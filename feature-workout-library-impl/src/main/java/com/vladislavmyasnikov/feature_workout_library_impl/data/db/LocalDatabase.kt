package com.vladislavmyasnikov.feature_workout_library_impl.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.converters.ListConverter
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.dao.WorkoutLibraryDao
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutExerciseEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutSetEntity

@Database(entities = [WorkoutExerciseEntity::class, WorkoutSetEntity::class, WorkoutEntity::class], version = 1, exportSchema = false)
@TypeConverters(value = [ListConverter::class])
abstract class LocalDatabase : RoomDatabase() {

    abstract fun workoutLibraryDao(): WorkoutLibraryDao
}