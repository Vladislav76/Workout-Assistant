package com.vladislavmyasnikov.feature_workout_library_impl.data.db.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class ShortWorkoutInfo(
        @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "avatar_id") val avatarID: String
)