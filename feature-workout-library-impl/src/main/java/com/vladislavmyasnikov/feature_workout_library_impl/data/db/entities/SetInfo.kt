package com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sets")
class SetInfo(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "exercises_ids") val exercisesIDs: List<Long>,
        @ColumnInfo(name = "exercises_reps") val exercisesReps: List<List<Int>>
)