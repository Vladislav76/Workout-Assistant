package com.vladislavmyasnikov.feature_exercise_library_impl.domain.repository

import com.vladislavmyasnikov.feature_exercise_library_impl.domain.entity.FullExercise
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.entity.ShortExercise
import io.reactivex.Observable
import io.reactivex.Single

interface ExerciseRepository {

    fun fetchShortExercisesInfo(): Observable<List<ShortExercise>>
    fun fetchFullExerciseInfo(id: Long): Single<FullExercise>
}