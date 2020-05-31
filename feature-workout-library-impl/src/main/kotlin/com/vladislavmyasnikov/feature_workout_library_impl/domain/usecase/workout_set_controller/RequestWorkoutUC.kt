package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller

interface RequestWorkoutUC {

    operator fun invoke(workoutPlanID: Long)
}