package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_controller

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExercise

interface GetWorkoutExerciseUC {

    fun getWorkoutExerciseById(exerciseID: Long): WorkoutExercise
}