package com.vladislavmyasnikov.feature_diary_impl.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary")
data class DiaryEntry(
        @PrimaryKey(autoGenerate = true) val id: Long = 0
)