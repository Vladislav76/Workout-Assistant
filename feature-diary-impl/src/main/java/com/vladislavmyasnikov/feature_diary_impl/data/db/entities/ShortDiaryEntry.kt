package com.vladislavmyasnikov.feature_diary_impl.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.vladislavmyasnikov.core_components.models.TimePoint
import java.sql.Time
import java.util.*

class ShortDiaryEntry(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "date") val date: Date,
        @ColumnInfo(name = "start_time") val startTime: TimePoint,
        @ColumnInfo(name = "end_time") val endTime: TimePoint,
        @ColumnInfo(name = "duration") val duration: TimePoint
)