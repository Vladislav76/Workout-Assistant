package com.vladislavmyasnikov.feature_workout_library_impl.domain

import io.reactivex.Observable
import io.reactivex.Single

interface WorkoutRepository {

    fun fetchShortWorkoutList(): Observable<List<ShortWorkout>>
    fun fetchFullWorkout(id: Long): Single<FullWorkout>
    fun fetchWorkoutSetList(id: Long): Single<List<WorkoutSet>>
}