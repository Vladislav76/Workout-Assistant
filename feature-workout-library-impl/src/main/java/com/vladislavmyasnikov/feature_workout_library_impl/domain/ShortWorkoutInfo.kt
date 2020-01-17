package com.vladislavmyasnikov.feature_workout_library_impl.domain

import com.vladislavmyasnikov.common.interfaces.Identifiable

open class ShortWorkoutInfo(
        override val id: Long,
        val title: String,
        val avatarID: String
) : Identifiable<ShortWorkoutInfo> {

    override fun isIdentical(another: ShortWorkoutInfo): Boolean = id == another.id
}