package com.vladislavmyasnikov.feature_diary_impl.data.db

import com.vladislavmyasnikov.feature_diary_impl.data.db.entities.FullDiaryEntry
import java.sql.Time
import java.util.*

fun generateEntries(amount: Int): List<FullDiaryEntry> {
    val entries = mutableListOf<FullDiaryEntry>()
    val date = Date()
    val calendar = GregorianCalendar(0, 0, 0, 0, 0)
    val time = Time(calendar.time.time)
    for (i in 1..amount) {
        entries.add(FullDiaryEntry(date = date, duration = time, startTime = time, endTime = time, description = ""))
    }
    return entries
}
