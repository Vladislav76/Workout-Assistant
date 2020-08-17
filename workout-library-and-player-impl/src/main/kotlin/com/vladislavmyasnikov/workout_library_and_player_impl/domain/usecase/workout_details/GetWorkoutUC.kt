package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_details

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout.Workout
import io.reactivex.Single

interface GetWorkoutUC {

    fun getWorkoutById(id: Long): Single<Workout>
}