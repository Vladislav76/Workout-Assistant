package com.vladislavmyasnikov.workout_library_and_player_impl.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladislavmyasnikov.common.models.TimePoint
import java.util.*

@Entity(tableName = "completed_workout")
class CompletedWorkoutEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "date") val date: Date,
        @ColumnInfo(name = "start_time") val startTime: TimePoint,
        @ColumnInfo(name = "end_time") val endTime: TimePoint,
        @ColumnInfo(name = "duration") val duration: TimePoint,
        @ColumnInfo(name = "description") val description: String,
        @ColumnInfo(name = "workout_productivity") val workoutProductivity: Int,
        @ColumnInfo(name = "workout_id") val workoutId: Long,
        val workoutName: String = ""
)