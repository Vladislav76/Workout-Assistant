package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_player

import io.reactivex.Completable

interface SaveCompletedWorkoutUC {

    fun saveCompletedWorkout(): Completable
}