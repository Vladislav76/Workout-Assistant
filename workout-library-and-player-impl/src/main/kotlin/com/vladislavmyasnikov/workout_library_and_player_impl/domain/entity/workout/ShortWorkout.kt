package com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout

import com.vladislavmyasnikov.common.interfaces.Identifiable

data class ShortWorkout(
        override val id: Long,
        val title: String,
        val avatarID: String
) : Identifiable<ShortWorkout> {

    override fun isIdentical(another: ShortWorkout): Boolean = id == another.id
}