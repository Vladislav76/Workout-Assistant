package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player

interface ChangeWorkoutProcessStateUC {

    fun start(workoutPlanID: Long)
    fun stop()
    fun pause()
    fun resume()
    fun next()
}