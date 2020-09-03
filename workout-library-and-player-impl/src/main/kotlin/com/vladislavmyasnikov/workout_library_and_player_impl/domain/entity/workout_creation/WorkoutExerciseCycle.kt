package com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation

import com.vladislavmyasnikov.common.interfaces.Identifiable

data class WorkoutExerciseCycle(
        override val id: Long,
        val reps: Int,
        val weight: Float
) : Identifiable<WorkoutExerciseCycle> {

    override fun isIdentical(another: WorkoutExerciseCycle) = id == another.id
}