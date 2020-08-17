package com.vladislavmyasnikov.workout_diary_impl.domain.usecase.completed_workout_list

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.workout_diary_impl.domain.repository.WorkoutDiaryRepository
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.ShortCompletedWorkout
import io.reactivex.Observable
import javax.inject.Inject

@PerScreen
class GetCompletedWorkoutListUCImpl @Inject constructor(
        private val workoutDiaryRepository: WorkoutDiaryRepository
) : GetCompletedWorkoutListUC {

    override fun getAllCompletedWorkouts(): Observable<List<ShortCompletedWorkout>> = workoutDiaryRepository.fetchAllCompletedWorkouts()
}