package com.vladislavmyasnikov.workout_diary_impl.domain.usecase.completed_workout_details

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.workout_diary_impl.domain.repository.WorkoutDiaryRepository
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.CompletedWorkout
import io.reactivex.Single
import javax.inject.Inject

@PerScreen
class GetCompletedWorkoutUCImpl @Inject constructor(
        private val workoutDiaryRepository: WorkoutDiaryRepository
) : GetCompletedWorkoutUC {

    override fun getCompletedWorkoutById(id: Long): Single<CompletedWorkout> = workoutDiaryRepository.fetchCompletedWorkoutById(id)
}