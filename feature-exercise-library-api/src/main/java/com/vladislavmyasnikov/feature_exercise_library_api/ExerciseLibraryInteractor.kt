package com.vladislavmyasnikov.feature_exercise_library_api

import com.vladislavmyasnikov.feature_exercise_library_api.ExerciseInfo
import io.reactivex.Single

interface ExerciseLibraryInteractor {

    fun fetchExercises(ids: List<Long>): Single<List<ExerciseInfo>>
}