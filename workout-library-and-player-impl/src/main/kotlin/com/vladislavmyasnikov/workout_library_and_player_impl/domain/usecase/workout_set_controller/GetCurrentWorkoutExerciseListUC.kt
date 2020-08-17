package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_controller

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExercise
import io.reactivex.Observable

interface GetCurrentWorkoutExerciseListUC {

    fun getCurrentWorkoutExercises(): Observable<List<WorkoutExercise>>
}