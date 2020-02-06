package com.vladislavmyasnikov.feature_workout_library_impl.domain

import com.vladislavmyasnikov.common.interfaces.Identifiable

data class WorkoutExercise(
        override val id: Long,
        val title: String,
        val reps: List<Int>,
        val weights: List<Float>,
        val avatarID: String
) : Identifiable<WorkoutExercise> {

    override fun isIdentical(another: WorkoutExercise): Boolean = id == another.id
}