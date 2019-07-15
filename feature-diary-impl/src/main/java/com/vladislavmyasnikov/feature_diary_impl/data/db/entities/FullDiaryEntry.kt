package com.vladislavmyasnikov.feature_diary_impl.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.*

@Entity(tableName = "diary")
class FullDiaryEntry(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "date") val date: Date,
        @ColumnInfo(name = "duration") val duration: Time,
        @ColumnInfo(name = "start_time") val startTime: Time,
        @ColumnInfo(name = "end_time") val endTime: Time,
        @ColumnInfo(name = "description") val description: String
)