package com.vladislavmyasnikov.feature_workout_library_api

interface WorkoutLibraryInteractor {

    fun fetchWorkoutNamesByIds(ids: List<Long>): List<String>
}