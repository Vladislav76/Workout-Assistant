package com.vladislavmyasnikov.feature_workout_library_impl.domain

import com.vladislavmyasnikov.core_utils.utils.interfaces.Identifiable

open class ShortWorkoutInfo(
        val id: Long,
        val title: String
) : Identifiable<ShortWorkoutInfo> {

    override fun isIdentical(another: ShortWorkoutInfo): Boolean = id == another.id
}