package com.vladislavmyasnikov.feature_workout_library_impl.domain

data class WorkoutSet(
        val id: Long,
        val workoutExercises: List<WorkoutExercise>
)