package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller

interface ChangeWorkoutSetConfigUC {

    fun putSetIndex(index: Int)
    fun putApproachIndex(index: Int)
}