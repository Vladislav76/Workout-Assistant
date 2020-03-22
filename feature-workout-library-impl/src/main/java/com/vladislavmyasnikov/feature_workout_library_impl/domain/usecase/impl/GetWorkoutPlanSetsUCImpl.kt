package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.impl

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutSet
import com.vladislavmyasnikov.feature_workout_library_impl.domain.repository.WorkoutRepository
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.GetWorkoutPlanSetsUC
import io.reactivex.Single
import javax.inject.Inject

class GetWorkoutPlanSetsUCImpl @Inject constructor (
        private val workoutRepository: WorkoutRepository
) : GetWorkoutPlanSetsUC {

    override fun invoke(workoutPlanID: Long): Single<List<WorkoutSet>> = workoutRepository.fetchWorkoutSetList(workoutPlanID)
}