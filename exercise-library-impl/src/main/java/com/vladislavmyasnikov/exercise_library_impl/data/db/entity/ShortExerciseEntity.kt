package com.vladislavmyasnikov.exercise_library_impl.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class ShortExerciseEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "muscle_groups_ids") val muscleGroupsIDs: List<Int>,
        @ColumnInfo(name = "avatar_id") val avatarID: String
)