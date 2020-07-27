package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_list

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutSet
import com.vladislavmyasnikov.feature_workout_library_impl.domain.repository.WorkoutRepository
import io.reactivex.Single
import javax.inject.Inject

class GetWorkoutSetListUCImpl @Inject constructor (
        private val workoutRepository: WorkoutRepository
) : GetWorkoutSetListUC {

    override fun getAllSetsByWorkoutId(id: Long): Single<List<WorkoutSet>> = workoutRepository.fetchWorkoutSetList(id)
}