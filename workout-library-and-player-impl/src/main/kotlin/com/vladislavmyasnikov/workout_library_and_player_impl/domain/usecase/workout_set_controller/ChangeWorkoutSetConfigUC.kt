package com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_controller

interface ChangeWorkoutSetConfigUC {

    fun putSetIndex(index: Int)
    fun putApproachIndex(index: Int)
}