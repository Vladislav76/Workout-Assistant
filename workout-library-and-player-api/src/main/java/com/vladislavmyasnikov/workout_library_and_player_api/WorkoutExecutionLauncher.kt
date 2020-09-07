package com.vladislavmyasnikov.workout_library_and_player_api

import com.vladislavmyasnikov.common.arch.component.FlowFragment

interface WorkoutExecutionLauncher {

    fun launch(workoutId: Long): FlowFragment
}