package com.vladislavmyasnikov.features_api.exercise_library

import io.reactivex.Single

interface ExerciseLibraryInteractor {

    fun fetchWorkoutExercisesInfo(ids: List<Long>): Single<List<WorkoutExerciseInfo>>
}