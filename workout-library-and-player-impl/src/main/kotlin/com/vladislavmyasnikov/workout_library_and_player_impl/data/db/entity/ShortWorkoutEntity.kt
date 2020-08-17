package com.vladislavmyasnikov.workout_library_and_player_impl.data.db.entity

import androidx.room.ColumnInfo

class ShortWorkoutEntity(
        @ColumnInfo(name = "id") val id: Long,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "avatar_id") val avatarID: String
)