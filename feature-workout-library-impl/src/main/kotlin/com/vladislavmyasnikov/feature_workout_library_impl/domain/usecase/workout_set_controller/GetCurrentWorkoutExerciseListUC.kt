package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutExercise
import io.reactivex.Observable

interface GetCurrentWorkoutExerciseListUC {

    fun getCurrentWorkoutExercises(): Observable<List<WorkoutExercise>>
}