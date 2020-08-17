package com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity

data class WorkoutSetConfig(
        val setIndex: Int,
        val approachIndex: Int,
        val setAmount: Int,
        val exerciseAmount: Int,
        val approachAmount: Int
)