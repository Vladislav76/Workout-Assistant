package com.vladislavmyasnikov.feature_exercise_library_impl.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_library")
class FullExerciseInfo(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "muscle_groups_ids") val muscleGroupsIDs: List<Int>,
        @ColumnInfo(name = "avatar_id") val avatarID: String,
        @ColumnInfo(name = "images_ids") val imagesIDs: List<String>,
        @ColumnInfo(name = "description") val description: String
)