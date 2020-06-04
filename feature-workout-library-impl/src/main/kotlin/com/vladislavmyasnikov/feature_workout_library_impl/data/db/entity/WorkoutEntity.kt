package com.vladislavmyasnikov.feature_workout_library_impl.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout")
class WorkoutEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "description") val description: String,
        @ColumnInfo(name = "workout_set_ids") val workoutSetIDs: List<Long>,
        @ColumnInfo(name = "avatar_id") val avatarID: String
)