package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller

import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutExercise

interface GetWorkoutExerciseUC {

    fun getWorkoutExerciseById(exerciseID: Long): WorkoutExercise
}