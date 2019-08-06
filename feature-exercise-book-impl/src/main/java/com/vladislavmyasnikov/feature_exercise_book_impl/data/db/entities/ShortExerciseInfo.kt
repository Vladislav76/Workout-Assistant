package com.vladislavmyasnikov.feature_exercise_book_impl.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class ShortExerciseInfo(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "title") val title: String
)