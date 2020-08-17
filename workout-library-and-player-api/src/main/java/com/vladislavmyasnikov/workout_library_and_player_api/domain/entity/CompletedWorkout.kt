package com.vladislavmyasnikov.workout_library_and_player_api.domain.entity

import com.vladislavmyasnikov.common.models.TimePoint
import java.util.*

data class CompletedWorkout(
        val id: Long,
        val date: Date,
        val startTime: TimePoint,
        val endTime: TimePoint,
        val duration: TimePoint,
        val description: String,
        val workoutProductivity: Int,
        val workoutId: Long,
        val workoutName: String
)