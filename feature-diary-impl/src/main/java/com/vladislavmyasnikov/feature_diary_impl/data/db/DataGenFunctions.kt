package com.vladislavmyasnikov.feature_diary_impl.data.db

import com.vladislavmyasnikov.core_components.models.TimePoint
import com.vladislavmyasnikov.feature_diary_impl.data.db.entities.FullDiaryEntry
import java.sql.Time
import java.util.*

fun generateEntries(amount: Int): List<FullDiaryEntry> {
    val entries = mutableListOf<FullDiaryEntry>()
    val date = Date()
    val time = TimePoint(0)
    for (i in 1..amount) {
        entries.add(FullDiaryEntry(date = date, duration = time, startTime = time, endTime = time, description = ""))
    }
    return entries
}
