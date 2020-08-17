package com.vladislavmyasnikov.workout_library_and_player_impl.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_exercise")
class WorkoutExerciseEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "exercise_id") val exerciseId: Long,
        @ColumnInfo(name = "reps") val reps: List<Int>,
        @ColumnInfo(name = "weights") val weights: List<Float>
)