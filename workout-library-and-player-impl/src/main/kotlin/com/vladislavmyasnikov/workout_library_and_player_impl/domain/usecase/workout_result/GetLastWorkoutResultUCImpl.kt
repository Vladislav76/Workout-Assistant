package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_result

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_execution.WorkoutResult
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.repository.WorkoutRepository
import io.reactivex.Single
import javax.inject.Inject

@PerScreen
class GetLastWorkoutResultUCImpl @Inject constructor (
    private val workoutRepository: WorkoutRepository
) : GetLastWorkoutResultUC {

    override fun invoke(): Single<WorkoutResult> = workoutRepository.fetchLastWorkoutResult()
}