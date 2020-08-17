package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_player

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExercise
import io.reactivex.Observable

interface GetCurrentWorkoutExerciseUC {

    fun getCurrentWorkoutExercise(): Observable<WorkoutExercise>
}