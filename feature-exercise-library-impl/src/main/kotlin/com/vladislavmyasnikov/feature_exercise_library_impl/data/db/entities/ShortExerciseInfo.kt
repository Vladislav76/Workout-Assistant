package com.vladislavmyasnikov.feature_exercise_library_impl.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class ShortExerciseInfo(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "muscle_groups_ids") val muscleGroupsIDs: List<Int>,
        @ColumnInfo(name = "avatar_id") val avatarID: String
)