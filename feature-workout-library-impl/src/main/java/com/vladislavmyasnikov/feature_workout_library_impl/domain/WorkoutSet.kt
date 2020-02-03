package com.vladislavmyasnikov.feature_workout_library_impl.domain

import com.vladislavmyasnikov.common.interfaces.Identifiable

class WorkoutSet(
        override val id: Long,
        val workoutExercises: List<WorkoutExercise>
) : Identifiable<WorkoutSet> {

    override fun isIdentical(another: WorkoutSet): Boolean = id == another.id
}