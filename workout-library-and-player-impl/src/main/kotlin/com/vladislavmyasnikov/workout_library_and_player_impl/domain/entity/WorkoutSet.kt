package com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity

data class WorkoutSet(
        val id: Long,
        val elements: List<WorkoutSetElement>
)