package com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation

import com.vladislavmyasnikov.common.interfaces.Identifiable

data class WorkoutSet(
        override val id: Long,
        val exerciseNameList: List<String>,
        val cycles: Int
) : Identifiable<WorkoutSet> {

    override fun isIdentical(another: WorkoutSet): Boolean = id == another.id
}