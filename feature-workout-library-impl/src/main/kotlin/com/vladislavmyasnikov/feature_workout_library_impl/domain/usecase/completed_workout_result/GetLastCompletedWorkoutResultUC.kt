package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.completed_workout_result

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.CompletedWorkoutResult
import io.reactivex.Single

interface GetLastCompletedWorkoutResultUC {

    operator fun invoke(): Single<CompletedWorkoutResult>
}