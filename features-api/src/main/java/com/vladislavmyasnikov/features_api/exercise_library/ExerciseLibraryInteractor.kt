package com.vladislavmyasnikov.features_api.exercise_library

import io.reactivex.Single

interface ExerciseLibraryInteractor {

    fun fetchExercises(ids: List<Long>): Single<List<ExerciseInfo>>
}