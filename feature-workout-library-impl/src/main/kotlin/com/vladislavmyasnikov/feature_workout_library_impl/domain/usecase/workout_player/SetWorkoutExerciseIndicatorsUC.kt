package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player

interface SetWorkoutExerciseIndicatorsUC {

    fun setRepetitions(reps: Int)
    fun setWeight(weight: Float)
}