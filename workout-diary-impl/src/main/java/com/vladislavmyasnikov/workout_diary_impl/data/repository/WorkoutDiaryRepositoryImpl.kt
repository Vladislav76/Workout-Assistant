package com.vladislavmyasnikov.workout_diary_impl.data.repository

import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.workout_diary_impl.domain.repository.WorkoutDiaryRepository
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutLibraryInteractor
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.CompletedWorkout
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.ShortCompletedWorkout
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@PerFlow
class WorkoutDiaryRepositoryImpl @Inject constructor(
        private val workoutLibraryInteractor: WorkoutLibraryInteractor
) : WorkoutDiaryRepository {

    override fun fetchAllCompletedWorkouts(): Observable<List<ShortCompletedWorkout>> {
        return workoutLibraryInteractor.fetchAllCompletedWorkouts()
    }

    override fun fetchCompletedWorkoutById(id: Long): Single<CompletedWorkout> {
        return workoutLibraryInteractor.fetchCompletedWorkoutById(id)
    }
}