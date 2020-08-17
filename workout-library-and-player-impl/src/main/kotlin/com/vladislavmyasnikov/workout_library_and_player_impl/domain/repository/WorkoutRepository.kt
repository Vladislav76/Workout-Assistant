package com.vladislavmyasnikov.workout_library_and_player_impl.domain.repository

import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.CompletedWorkout
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutSet
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_execution.WorkoutResult
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout.ShortWorkout
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout.Workout
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface WorkoutRepository {

    fun fetchShortWorkoutList(): Observable<List<ShortWorkout>>
    fun fetchWorkoutById(id: Long): Single<Workout>
    fun fetchWorkoutSetList(id: Long): Single<List<WorkoutSet>>
    fun fetchLastWorkoutResult(): Single<WorkoutResult>
    fun saveCompletedWorkout(workout: CompletedWorkout): Completable
}