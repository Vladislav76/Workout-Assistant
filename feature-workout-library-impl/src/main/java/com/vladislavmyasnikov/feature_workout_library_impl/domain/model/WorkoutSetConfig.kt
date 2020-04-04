package com.vladislavmyasnikov.feature_workout_library_impl.domain.model

data class WorkoutSetConfig(
        val setIndex: Int,
        val approachIndex: Int,
        val setAmount: Int,
        val exerciseAmount: Int,
        val approachAmount: Int
)