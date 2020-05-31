package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExecutionStatus
import io.reactivex.Observable

interface GetWorkoutProcessStateUC {

    operator fun invoke(spike: Long = 0): Observable<WorkoutExecutionStatus>
}