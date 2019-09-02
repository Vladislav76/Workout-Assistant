package com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
class WorkoutInfo(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "description") val description: String,
        @ColumnInfo(name = "sets_ids") val setsIDs: List<Long>,
        @ColumnInfo(name = "avatar_id") val avatarID: String
)