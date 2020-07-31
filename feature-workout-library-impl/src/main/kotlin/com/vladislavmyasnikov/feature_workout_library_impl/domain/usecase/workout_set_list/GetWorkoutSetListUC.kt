package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_list

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutSet
import io.reactivex.Single

interface GetWorkoutSetListUC {

    fun getAllSetsByWorkoutId(id: Long): Single<List<WorkoutSet>>
}