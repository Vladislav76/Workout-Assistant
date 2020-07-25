package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.TimerValue
import io.reactivex.Observable

interface GetWorkoutTimerValueUC {

    fun currentTimer(): Observable<TimerValue>
}