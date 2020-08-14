package com.vladislavmyasnikov.feature_workout_library_impl.domain.repository

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.workout.Workout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.workout.ShortWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutSet
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.CompletedWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.CompletedWorkoutResult
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.ShortCompletedWorkout
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface WorkoutRepository {

    fun fetchShortWorkoutList(): Observable<List<ShortWorkout>>
    fun fetchAllCompletedWorkouts(): Observable<List<ShortCompletedWorkout>>
    fun fetchWorkoutById(id: Long): Single<Workout>
    fun fetchCompletedWorkoutById(id: Long): Single<CompletedWorkout>
    fun fetchWorkoutSetList(id: Long): Single<List<WorkoutSet>>
    fun fetchLastCompletedWorkoutResult(): Single<CompletedWorkoutResult>
    fun saveCompletedWorkout(workout: CompletedWorkout): Completable
}