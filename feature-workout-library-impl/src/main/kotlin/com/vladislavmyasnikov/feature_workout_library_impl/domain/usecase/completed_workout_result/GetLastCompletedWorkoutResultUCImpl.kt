package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.completed_workout_result

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.CompletedWorkoutResult
import com.vladislavmyasnikov.feature_workout_library_impl.domain.repository.WorkoutRepository
import io.reactivex.Single
import javax.inject.Inject

@PerScreen
class GetLastCompletedWorkoutResultUCImpl @Inject constructor (
    private val workoutRepository: WorkoutRepository
) : GetLastCompletedWorkoutResultUC {

    override fun invoke(): Single<CompletedWorkoutResult> = workoutRepository.fetchLastCompletedWorkoutResult()
}