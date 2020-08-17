package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_player

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_execution.TimerValue
import io.reactivex.Observable

interface GetCurrentWorkoutTimerValueUC {

    fun getCurrentWorkoutTimerValue(): Observable<TimerValue>
}