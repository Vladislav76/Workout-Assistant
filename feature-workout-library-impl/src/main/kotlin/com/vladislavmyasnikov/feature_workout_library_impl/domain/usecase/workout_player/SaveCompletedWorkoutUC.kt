package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player

import io.reactivex.Completable

interface SaveCompletedWorkoutUC {

    fun saveCompletedWorkout(): Completable
}