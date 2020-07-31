package com.vladislavmyasnikov.feature_workout_library_impl.domain.repository

import com.vladislavmyasnikov.feature_diary_api.domain.entity.FullDiaryEntry
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.FullWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.ShortWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutSet
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface WorkoutRepository {

    fun fetchShortWorkoutList(): Observable<List<ShortWorkout>>
    fun fetchFullWorkout(id: Long): Single<FullWorkout>
    fun fetchWorkoutSetList(id: Long): Single<List<WorkoutSet>>
    fun saveWorkoutResult(result: FullDiaryEntry): Completable
}