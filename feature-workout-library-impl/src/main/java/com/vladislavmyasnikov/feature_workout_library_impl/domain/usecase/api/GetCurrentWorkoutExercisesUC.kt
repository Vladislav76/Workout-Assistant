package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise
import io.reactivex.Observable

interface GetCurrentWorkoutExercisesUC {

    operator fun invoke(spike: Int = 0): Observable<List<WorkoutExercise>>
}