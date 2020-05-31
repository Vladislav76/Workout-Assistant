package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_list

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutSet
import io.reactivex.Single

interface GetWorkoutSetListUC {

    operator fun invoke(workoutPlanID: Long): Single<List<WorkoutSet>>
}