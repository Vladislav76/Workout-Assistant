package com.vladislavmyasnikov.feature_exercise_library_impl.domain

import io.reactivex.Observable
import io.reactivex.Single

interface ExerciseRepository {

    fun fetchShortExercisesInfo(): Observable<List<ShortExerciseInfo>>
    fun fetchFullExerciseInfo(id: Long): Single<FullExerciseInfo>
}