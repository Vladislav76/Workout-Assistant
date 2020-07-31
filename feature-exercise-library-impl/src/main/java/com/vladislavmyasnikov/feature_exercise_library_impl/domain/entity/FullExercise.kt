package com.vladislavmyasnikov.feature_exercise_library_impl.domain.entity

data class FullExercise(
        val id: Long,
        val title: String,
        val muscleGroupsIDs: List<Int>,
        val avatarID: String,
        val imagesIDs: List<String>,
        val description: String
)