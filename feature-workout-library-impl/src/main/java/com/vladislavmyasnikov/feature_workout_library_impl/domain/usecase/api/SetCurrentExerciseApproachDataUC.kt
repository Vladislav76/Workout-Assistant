package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api

interface SetCurrentExerciseApproachDataUC {

    fun setRepetitions(reps: Int)
    fun setWeight(weight: Float)
}