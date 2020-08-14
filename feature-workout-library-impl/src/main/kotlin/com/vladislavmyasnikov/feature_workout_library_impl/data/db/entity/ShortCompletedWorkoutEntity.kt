package com.vladislavmyasnikov.feature_workout_library_impl.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.vladislavmyasnikov.common.models.TimePoint
import java.util.*

class ShortCompletedWorkoutEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "date") val date: Date,
        @ColumnInfo(name = "duration") val duration: TimePoint,
        @ColumnInfo(name = "workout_productivity") val workoutProductivity: Int,
        @ColumnInfo(name = "workout_name") val workoutName: String
)