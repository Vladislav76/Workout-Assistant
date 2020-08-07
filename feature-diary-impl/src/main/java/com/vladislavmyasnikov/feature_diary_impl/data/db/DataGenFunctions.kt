package com.vladislavmyasnikov.feature_diary_impl.data.db

import com.vladislavmyasnikov.common.models.TimePoint
import com.vladislavmyasnikov.feature_diary_impl.data.db.entity.DiaryEntryEntity
import java.util.*

fun generateEntries(amount: Int): List<DiaryEntryEntity> {
    val entries = mutableListOf<DiaryEntryEntity>()
    val date = Date()
    val time = TimePoint(0)
    for (i in 1..amount) {
        entries.add(DiaryEntryEntity(date = date, duration = time, startTime = time, endTime = time, description = "", workoutProductivity = 0))
    }
    return entries
}
