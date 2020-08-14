package com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout

import com.vladislavmyasnikov.common.models.TimePoint

data class CompletedWorkoutResult(
        val duration: TimePoint,
        val workoutProductivity: Int
)