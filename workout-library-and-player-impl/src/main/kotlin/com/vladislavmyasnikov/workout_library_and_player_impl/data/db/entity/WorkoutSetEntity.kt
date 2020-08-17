package com.vladislavmyasnikov.workout_library_and_player_impl.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout_set")
class WorkoutSetEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "workout_exercise_ids") val workoutExerciseIDs: List<Long>
)