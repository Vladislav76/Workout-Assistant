package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_result

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutResult
import com.vladislavmyasnikov.feature_workout_library_impl.domain.repository.WorkoutRepository
import io.reactivex.Single
import javax.inject.Inject

@PerScreen
class GetLastSavedWorkoutResultUCImpl @Inject constructor (
    private val workoutRepository: WorkoutRepository
) : GetLastSavedWorkoutResultUC {

    override fun invoke(): Single<WorkoutResult> = workoutRepository.fetchLastSavedWorkoutResult()
}