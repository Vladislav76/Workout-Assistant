package com.vladislavmyasnikov.feature_diary_impl.data.db

import com.vladislavmyasnikov.common.models.TimePoint
import com.vladislavmyasnikov.feature_diary_impl.data.db.entity.DiaryEntryEntity
import java.util.*
import kotlin.random.Random

fun generateDiaryEntry(): DiaryEntryEntity {
    val date = Date(Random.nextLong(0, 1_000_000_000_000))
    val ms = Random.nextLong(0, 4_000_000)
    val time1 = TimePoint(ms)
    val time2 = TimePoint(ms + Random.nextLong(10_000, 4_000_000))
    val time3 = TimePoint(time2.toMilliseconds() - time1.toMilliseconds())
    val productivity = Random.nextInt(0, 100)
    val id = Random.nextLong(1, 20)
    return DiaryEntryEntity(date = date, duration = time3, startTime = time1, endTime = time2, description = "TBD", workoutProductivity = productivity, workoutId = id)
}

fun generateEntries(amount: Int): List<DiaryEntryEntity> {
    return mutableListOf<DiaryEntryEntity>().apply {
        for (i in 1..amount) {
            add(generateDiaryEntry())
        }
    }
}
