package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api

interface ChangeWorkoutSetConfigUC {

    fun putSetIndex(setIndex: Int)
    fun putApproachIndex(approachIndex: Int)
}