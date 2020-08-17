package com.vladislavmyasnikov.exercise_library_api

import io.reactivex.Single

interface ExerciseLibraryInteractor {

    fun fetchExercises(ids: List<Long>): Single<List<ExerciseInfo>>
}