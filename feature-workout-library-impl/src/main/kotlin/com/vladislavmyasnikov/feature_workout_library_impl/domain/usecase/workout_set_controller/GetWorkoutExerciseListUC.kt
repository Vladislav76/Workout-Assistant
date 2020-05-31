package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise
import io.reactivex.Observable

interface GetWorkoutExerciseListUC {

    operator fun invoke(spike: Int = 0): Observable<List<WorkoutExercise>>
}