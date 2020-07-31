package com.vladislavmyasnikov.feature_workout_library_impl.domain.entity

import com.vladislavmyasnikov.common.interfaces.Identifiable

data class ShortWorkout(
        override val id: Long,
        val title: String,
        val avatarID: String
) : Identifiable<ShortWorkout> {

    override fun isIdentical(another: ShortWorkout): Boolean = id == another.id
}