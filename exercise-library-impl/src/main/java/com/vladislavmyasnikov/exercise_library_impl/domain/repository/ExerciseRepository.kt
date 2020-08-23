package com.vladislavmyasnikov.exercise_library_impl.domain.repository

import com.vladislavmyasnikov.exercise_library_impl.domain.entity.Exercise
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.ShortExercise
import io.reactivex.Observable
import io.reactivex.Single

interface ExerciseRepository {

    fun fetchAllExercises(): Observable<List<ShortExercise>>
    fun fetchExerciseById(id: Long): Single<Exercise>
}