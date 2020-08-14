package com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.workout

data class Workout(
        val id: Long,
        val title: String,
        val avatarID: String,
        val description: String
)