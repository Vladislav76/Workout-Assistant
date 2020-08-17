package com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_execution

import com.vladislavmyasnikov.common.models.TimePoint

data class WorkoutResult(
        val duration: TimePoint,
        val workoutProductivity: Int
)