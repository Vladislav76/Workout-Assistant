package com.vladislavmyasnikov.workout_library_and_player_api.domain.entity

import com.vladislavmyasnikov.common.models.TimePoint
import com.vladislavmyasnikov.common.interfaces.Identifiable
import java.util.*

data class ShortCompletedWorkout(
        override val id: Long,
        val date: Date,
        val duration: TimePoint,
        val workoutProductivity: Int,
        val workoutName: String
) : Identifiable<ShortCompletedWorkout> {

    override fun isIdentical(another: ShortCompletedWorkout): Boolean = id == another.id
}