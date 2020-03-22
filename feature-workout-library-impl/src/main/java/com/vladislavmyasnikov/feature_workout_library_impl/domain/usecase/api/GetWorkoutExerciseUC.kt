package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise
import io.reactivex.Single

interface GetWorkoutExerciseUC {

    operator fun invoke(exerciseID: Long): WorkoutExercise
}