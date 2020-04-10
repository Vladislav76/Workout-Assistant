package com.vladislavmyasnikov.feature_exercise_library_impl.domain.repository

import com.vladislavmyasnikov.feature_exercise_library_impl.domain.model.FullExercise
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.model.ShortExercise
import io.reactivex.Observable
import io.reactivex.Single

interface ExerciseRepository {

    fun fetchShortExercisesInfo(): Observable<List<ShortExercise>>
    fun fetchFullExerciseInfo(id: Long): Single<FullExercise>
}