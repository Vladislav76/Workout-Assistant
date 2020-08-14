package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_details

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.workout.Workout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.repository.WorkoutRepository
import io.reactivex.Single
import javax.inject.Inject

class GetWorkoutUCImpl @Inject constructor(
        private val workoutRepository: WorkoutRepository
) : GetWorkoutUC {

    override fun getWorkoutById(id: Long): Single<Workout> = workoutRepository.fetchWorkoutById(id)
}