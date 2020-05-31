package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller

interface ChangeWorkoutSetConfigUC {

    fun putSetIndex(setIndex: Int)
    fun putApproachIndex(approachIndex: Int)
}