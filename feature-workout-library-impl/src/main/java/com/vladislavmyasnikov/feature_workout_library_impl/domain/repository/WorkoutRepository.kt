package com.vladislavmyasnikov.feature_workout_library_impl.domain.repository

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.FullWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.ShortWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutSet
import io.reactivex.Observable
import io.reactivex.Single

interface WorkoutRepository {

    fun fetchShortWorkoutList(): Observable<List<ShortWorkout>>
    fun fetchFullWorkout(id: Long): Single<FullWorkout>
    fun fetchWorkoutSetList(id: Long): Single<List<WorkoutSet>>
}