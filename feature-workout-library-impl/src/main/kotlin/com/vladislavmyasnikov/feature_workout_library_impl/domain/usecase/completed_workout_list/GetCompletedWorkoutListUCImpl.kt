package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.completed_workout_list

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.ShortCompletedWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.repository.WorkoutRepository
import io.reactivex.Observable
import javax.inject.Inject

@PerScreen
class GetCompletedWorkoutListUCImpl @Inject constructor(
        private val workoutRepository: WorkoutRepository
) : GetCompletedWorkoutListUC {

    override fun getAllCompletedWorkouts(): Observable<List<ShortCompletedWorkout>> = workoutRepository.fetchAllCompletedWorkouts()
}