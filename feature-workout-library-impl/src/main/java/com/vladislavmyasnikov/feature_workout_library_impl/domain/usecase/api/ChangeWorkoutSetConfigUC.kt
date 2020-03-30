package com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api

interface ChangeWorkoutSetConfigUC {

    fun setNumber(number: Int)
    fun setApproach(approach: Int)
}