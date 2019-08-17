package com.vladislavmyasnikov.feature_exercise_library_impl.domain

import io.reactivex.Single

interface ExerciseRepository {

    fun fetchShortExercisesInfo(): Single<List<ShortExerciseInfo>>
}