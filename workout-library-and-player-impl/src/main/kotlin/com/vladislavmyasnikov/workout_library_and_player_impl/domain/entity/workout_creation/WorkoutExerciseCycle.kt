package com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation

import com.vladislavmyasnikov.common.interfaces.Identifiable
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExerciseIndicators

data class WorkoutExerciseCycle(
        override val id: Long,
        val data: WorkoutExerciseIndicators,
) : Identifiable<WorkoutExerciseCycle> {

    override fun isIdentical(another: WorkoutExerciseCycle) = id == another.id
}