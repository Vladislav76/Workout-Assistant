package com.vladislavmyasnikov.feature_workout_library_impl.domain.model

data class WorkoutExerciseConfig(
        val setIndex: Int,
        val exerciseIndex: Int,
        val approachIndex: Int,
        val setAmount: Int,
        val exerciseAmount: Int,
        val approachAmount: Int
)