package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.completed_workout_details

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.CompletedWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.repository.WorkoutRepository
import io.reactivex.Single
import javax.inject.Inject

@PerScreen
class GetCompletedWorkoutUCImpl @Inject constructor(
        private val workoutRepository: WorkoutRepository
) : GetCompletedWorkoutUC {

    override fun getCompletedWorkoutById(id: Long): Single<CompletedWorkout> = workoutRepository.fetchCompletedWorkoutById(id)
}