package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.impl

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.FullWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.repository.WorkoutRepository
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.GetWorkoutPlanUC
import io.reactivex.Single
import javax.inject.Inject

class GetWorkoutPlanUCImpl @Inject constructor(
        private val workoutRepository: WorkoutRepository
) : GetWorkoutPlanUC {

    override fun invoke(workoutPlanID: Long): Single<FullWorkout> = workoutRepository.fetchFullWorkout(workoutPlanID)
}