package com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation

import com.vladislavmyasnikov.common.interfaces.Identifiable

data class WorkoutExercise(
        override val id: Long,
        val name: String,
        val avatarId: String,
        val cycles: List<WorkoutExerciseCycle>
) : Identifiable<WorkoutExercise> {

    override fun isIdentical(another: WorkoutExercise): Boolean = id == another.id
}