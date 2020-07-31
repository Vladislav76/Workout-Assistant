package com.vladislavmyasnikov.feature_diary_impl.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vladislavmyasnikov.common.models.TimePoint
import java.util.*

@Entity(tableName = "diary")
class FullDiaryEntry(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "date") val date: Date,
        @ColumnInfo(name = "start_time") val startTime: TimePoint,
        @ColumnInfo(name = "end_time") val endTime: TimePoint,
        @ColumnInfo(name = "duration") val duration: TimePoint,
        @ColumnInfo(name = "description") val description: String
)