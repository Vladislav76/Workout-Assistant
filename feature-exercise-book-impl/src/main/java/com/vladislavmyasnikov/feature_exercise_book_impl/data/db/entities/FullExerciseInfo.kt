package com.vladislavmyasnikov.feature_exercise_book_impl.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_book")
class FullExerciseInfo(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "muscle_groups_ids") val muscleGroupsIDs: List<Int>,
        @ColumnInfo(name = "description") val description: String
)