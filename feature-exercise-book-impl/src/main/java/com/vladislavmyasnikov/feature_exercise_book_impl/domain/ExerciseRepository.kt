package com.vladislavmyasnikov.feature_exercise_book_impl.domain

import io.reactivex.Single

interface ExerciseRepository {

    fun fetchShortExercisesInfo(): Single<List<ShortExerciseInfo>>
}