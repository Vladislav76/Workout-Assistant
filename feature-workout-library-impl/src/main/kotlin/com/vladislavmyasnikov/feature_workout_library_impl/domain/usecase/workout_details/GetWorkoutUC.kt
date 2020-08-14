package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_details

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.workout.Workout
import io.reactivex.Single

interface GetWorkoutUC {

    fun getWorkoutById(id: Long): Single<Workout>
}