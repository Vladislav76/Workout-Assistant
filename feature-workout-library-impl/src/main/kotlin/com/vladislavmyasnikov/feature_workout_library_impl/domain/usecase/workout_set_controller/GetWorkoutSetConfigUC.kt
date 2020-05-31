package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutSetConfig
import io.reactivex.Observable

interface GetWorkoutSetConfigUC {

    operator fun invoke(): Observable<WorkoutSetConfig>
}