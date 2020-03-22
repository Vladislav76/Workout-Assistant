package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.FullWorkout
import io.reactivex.Single

interface GetWorkoutPlanUC {

    operator fun invoke(workoutPlanID: Long): Single<FullWorkout>
}