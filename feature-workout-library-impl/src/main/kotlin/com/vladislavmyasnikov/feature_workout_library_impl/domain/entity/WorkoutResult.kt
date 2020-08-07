package com.vladislavmyasnikov.feature_workout_library_impl.domain.entity

import com.vladislavmyasnikov.common.models.TimePoint

data class WorkoutResult(
        val duration: TimePoint,
        val productivity: Int
)