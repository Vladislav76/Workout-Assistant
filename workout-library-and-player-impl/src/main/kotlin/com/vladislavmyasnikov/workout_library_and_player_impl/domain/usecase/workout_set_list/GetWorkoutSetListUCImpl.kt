package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_list

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutSet
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.repository.WorkoutRepository
import io.reactivex.Single
import javax.inject.Inject

class GetWorkoutSetListUCImpl @Inject constructor (
        private val workoutRepository: WorkoutRepository
) : GetWorkoutSetListUC {

    override fun getAllSetsByWorkoutId(id: Long): Single<List<WorkoutSet>> = workoutRepository.fetchWorkoutSetList(id)
}