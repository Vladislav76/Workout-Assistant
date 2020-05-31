package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller

import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise

interface GetSelectedWorkoutExerciseUC {

    operator fun invoke(exerciseID: Long, spike: Int = 0): WorkoutExercise
}