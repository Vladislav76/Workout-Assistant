package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api

interface RequestWorkoutPlanInfoUC {

    operator fun invoke(workoutPlanID: Long)
}