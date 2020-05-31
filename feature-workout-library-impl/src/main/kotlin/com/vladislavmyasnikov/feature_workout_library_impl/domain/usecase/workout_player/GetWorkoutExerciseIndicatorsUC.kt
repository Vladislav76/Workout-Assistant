package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExerciseIndicators
import io.reactivex.Observable

interface GetWorkoutExerciseIndicatorsUC {

    operator fun invoke(spike: Int = 0, spike2: Int = 0): Observable<WorkoutExerciseIndicators>
}