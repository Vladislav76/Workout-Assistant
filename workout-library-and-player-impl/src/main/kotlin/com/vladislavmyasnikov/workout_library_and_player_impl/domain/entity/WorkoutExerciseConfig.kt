package com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity

data class WorkoutExerciseConfig(
        val setIndex: Int,
        val exerciseIndex: Int,
        val approachIndex: Int,
        val setAmount: Int,
        val exerciseAmount: Int,
        val approachAmount: Int
)