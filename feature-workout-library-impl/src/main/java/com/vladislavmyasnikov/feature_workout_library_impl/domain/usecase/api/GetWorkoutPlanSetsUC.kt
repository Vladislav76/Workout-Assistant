package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutSet
import io.reactivex.Single

interface GetWorkoutPlanSetsUC {

    operator fun invoke(workoutPlanID: Long): Single<List<WorkoutSet>>
}