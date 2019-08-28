package com.vladislavmyasnikov.feature_workout_library_impl.domain

import io.reactivex.Observable
import io.reactivex.Single

interface WorkoutRepository {

    fun fetchShortWorkoutsInfo(): Observable<List<ShortWorkoutInfo>>
    fun fetchFullWorkoutInfo(id: Long): Single<FullWorkoutInfo>
}