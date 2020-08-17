package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_controller

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutSetConfig
import io.reactivex.Observable

interface GetCurrentWorkoutSetConfigUC {

    fun getCurrentWorkoutSetConfig(): Observable<WorkoutSetConfig>
}