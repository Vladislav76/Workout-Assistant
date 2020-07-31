package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutExerciseIndicators
import io.reactivex.Observable

interface AccessWorkoutExerciseMetricsUC {

    fun getMetrics(): Observable<WorkoutExerciseIndicators>
    fun setRepetitionMetrics(value: Int)
    fun setWeightMetrics(value: Float)
}