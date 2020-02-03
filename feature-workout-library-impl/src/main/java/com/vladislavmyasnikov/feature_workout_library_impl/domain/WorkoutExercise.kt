package com.vladislavmyasnikov.feature_workout_library_impl.domain

class WorkoutExercise(
        val id: Long,
        val title: String,
        val reps: List<Int>,
        val weights: List<Float>,
        val avararID: String
)