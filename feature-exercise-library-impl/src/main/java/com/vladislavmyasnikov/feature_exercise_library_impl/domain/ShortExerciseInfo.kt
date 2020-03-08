package com.vladislavmyasnikov.feature_exercise_library_impl.domain

import com.vladislavmyasnikov.common.interfaces.Identifiable

open class ShortExerciseInfo(
        override val id: Long,
        val title: String,
        val muscleGroupsIDs: List<Int>,
        val avatarID: String
) : Identifiable<ShortExerciseInfo> {

    override fun isIdentical(another: ShortExerciseInfo): Boolean = id == another.id
}