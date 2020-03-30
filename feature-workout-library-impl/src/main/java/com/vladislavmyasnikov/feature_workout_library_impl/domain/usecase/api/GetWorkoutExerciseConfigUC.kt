package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExerciseConfig
import io.reactivex.Observable

interface GetWorkoutExerciseConfigUC {

    operator fun invoke(): Observable<WorkoutExerciseConfig>
}