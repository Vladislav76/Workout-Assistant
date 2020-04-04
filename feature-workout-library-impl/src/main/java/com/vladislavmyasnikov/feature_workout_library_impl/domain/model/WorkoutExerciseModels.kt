package com.vladislavmyasnikov.feature_workout_library_impl.domain.model

import com.vladislavmyasnikov.common.interfaces.Identifiable

data class WorkoutExerciseInfo(
        override val id: Long,
        val title: String,
        val avatarID: String
) : Identifiable<WorkoutExerciseInfo> {

    override fun isIdentical(another: WorkoutExerciseInfo): Boolean = id == another.id
}

data class ExerciseApproachData(
        var reps: Int,
        var weight: Float
)

data class WorkoutExercise(
        val info: WorkoutExerciseInfo,
        val exerciseApproachData: ExerciseApproachData
) : Identifiable<WorkoutExercise> {

    override val id = info.id

    override fun isIdentical(another: WorkoutExercise): Boolean = id == another.id
}

typealias WorkoutSetElement = Pair<WorkoutExerciseInfo, List<ExerciseApproachData>>