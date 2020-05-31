package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_details

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.FullWorkout
import io.reactivex.Single

interface GetWorkoutUC {

    operator fun invoke(workoutPlanID: Long): Single<FullWorkout>
}