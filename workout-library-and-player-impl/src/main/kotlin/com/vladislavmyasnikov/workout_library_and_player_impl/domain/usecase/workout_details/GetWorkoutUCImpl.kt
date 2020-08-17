package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_details

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout.Workout
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.repository.WorkoutRepository
import io.reactivex.Single
import javax.inject.Inject

class GetWorkoutUCImpl @Inject constructor(
        private val workoutRepository: WorkoutRepository
) : GetWorkoutUC {

    override fun getWorkoutById(id: Long): Single<Workout> = workoutRepository.fetchWorkoutById(id)
}