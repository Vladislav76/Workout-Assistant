package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutExerciseConfig
import io.reactivex.Observable

interface GetCurrentWorkoutExerciseConfigUC {

    fun getCurrentWorkoutExerciseConfig(): Observable<WorkoutExerciseConfig>
}