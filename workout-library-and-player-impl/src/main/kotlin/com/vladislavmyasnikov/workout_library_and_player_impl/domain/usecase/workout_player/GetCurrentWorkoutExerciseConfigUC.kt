package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_player

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExerciseConfig
import io.reactivex.Observable

interface GetCurrentWorkoutExerciseConfigUC {

    fun getCurrentWorkoutExerciseConfig(): Observable<WorkoutExerciseConfig>
}