package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api

interface ManageExecutingWorkoutUC {

    fun start(workoutPlanID: Long)
    fun stop()
    fun pause()
    fun resume()
    fun next()
}