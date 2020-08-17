package com.vladislavmyasnikov.workout_library_and_player_api

import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.CompletedWorkout
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.ShortCompletedWorkout
import io.reactivex.Observable
import io.reactivex.Single

interface WorkoutLibraryInteractor {

    fun fetchAllCompletedWorkouts(): Observable<List<ShortCompletedWorkout>>
    fun fetchCompletedWorkoutById(id: Long): Single<CompletedWorkout>
}