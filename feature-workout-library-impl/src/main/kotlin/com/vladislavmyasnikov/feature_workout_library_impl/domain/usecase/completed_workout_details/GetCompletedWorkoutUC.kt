package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.completed_workout_details

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.CompletedWorkout
import io.reactivex.Single

interface GetCompletedWorkoutUC {

    fun getCompletedWorkoutById(id: Long): Single<CompletedWorkout>
}