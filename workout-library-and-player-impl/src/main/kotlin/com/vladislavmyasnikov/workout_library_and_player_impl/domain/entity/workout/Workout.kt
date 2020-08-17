package com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout

data class Workout(
        val id: Long,
        val title: String,
        val avatarID: String,
        val description: String
)