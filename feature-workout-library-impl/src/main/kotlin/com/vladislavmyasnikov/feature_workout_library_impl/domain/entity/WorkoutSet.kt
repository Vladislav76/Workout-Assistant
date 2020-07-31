package com.vladislavmyasnikov.feature_workout_library_impl.domain.entity

data class WorkoutSet(
        val id: Long,
        val elements: List<WorkoutSetElement>
)