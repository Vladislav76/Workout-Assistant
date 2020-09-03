package com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation

import com.vladislavmyasnikov.common.interfaces.Identifiable
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExerciseIndicators

data class WorkoutExercise(
        override val id: Long,
        val name: String,
        val avatarId: String,
        val cycles: List<WorkoutExerciseIndicators>
) : Identifiable<WorkoutExercise> {

    override fun isIdentical(another: WorkoutExercise): Boolean = id == another.id
}