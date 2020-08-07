package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_result

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutResult
import io.reactivex.Single

interface GetLastSavedWorkoutResultUC {

    operator fun invoke(): Single<WorkoutResult>
}