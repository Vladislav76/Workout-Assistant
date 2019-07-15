package com.vladislavmyasnikov.feature_diary_impl.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.*

open class ShortDiaryEntry(
        @PrimaryKey(autoGenerate = true) open val id: Long = 0,
        @ColumnInfo(name = "date") open val date: Date,
        @ColumnInfo(name = "duration") open val duration: Time
)