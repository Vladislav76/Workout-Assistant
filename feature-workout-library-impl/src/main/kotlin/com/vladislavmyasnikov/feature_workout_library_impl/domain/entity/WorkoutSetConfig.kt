package com.vladislavmyasnikov.feature_workout_library_impl.domain.entity

data class WorkoutSetConfig(
        val setIndex: Int,
        val approachIndex: Int,
        val setAmount: Int,
        val exerciseAmount: Int,
        val approachAmount: Int
)