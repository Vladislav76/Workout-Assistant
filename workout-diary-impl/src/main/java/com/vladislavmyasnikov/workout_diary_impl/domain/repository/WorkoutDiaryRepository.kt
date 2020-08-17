package com.vladislavmyasnikov.workout_diary_impl.domain.repository

import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.CompletedWorkout
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.ShortCompletedWorkout
import io.reactivex.Observable
import io.reactivex.Single

interface WorkoutDiaryRepository {

    fun fetchAllCompletedWorkouts(): Observable<List<ShortCompletedWorkout>>
    fun fetchCompletedWorkoutById(id: Long): Single<CompletedWorkout>
}