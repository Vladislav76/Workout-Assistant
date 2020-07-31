package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutSetConfig
import io.reactivex.Observable

interface GetCurrentWorkoutSetConfigUC {

    fun getCurrentWorkoutSetConfig(): Observable<WorkoutSetConfig>
}