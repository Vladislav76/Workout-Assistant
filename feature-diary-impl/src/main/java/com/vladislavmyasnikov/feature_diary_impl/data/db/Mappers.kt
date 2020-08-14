package com.vladislavmyasnikov.feature_diary_impl.data.db

import com.vladislavmyasnikov.common.utils.Mapper
import com.vladislavmyasnikov.feature_diary_api.domain.entity.DiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.data.db.entity.DiaryEntryEntity
import com.vladislavmyasnikov.feature_diary_impl.data.db.entity.ShortDiaryEntryEntity
import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry

// DATABASE -> DOMAIN (short)
object EntityToModelShortDiaryEntryMapper : Mapper<ShortDiaryEntryEntity, ShortDiaryEntry>() {

    override fun map(value: ShortDiaryEntryEntity): ShortDiaryEntry {
        return ShortDiaryEntry(value.id, value.date, value.duration, value.workoutProductivity, "workout_name")
    }
}

// DATABASE -> DOMAIN (full)
object EntityToModelFullDiaryEntryMapper : Mapper<DiaryEntryEntity, DiaryEntry>() {

    override fun map(value: DiaryEntryEntity): DiaryEntry {
        return DiaryEntry(value.id, value.date, value.startTime, value.endTime, value.duration, value.description, value.workoutProductivity, value.workoutId)
    }
}

// DOMAIN -> DATABASE (full)
object ModelToEntityFullDiaryEntryMapper : Mapper<DiaryEntry, DiaryEntryEntity>() {

    override fun map(value: DiaryEntry): DiaryEntryEntity {
        return DiaryEntryEntity(value.id, value.date, value.startTime, value.endTime, value.duration, value.description, value.workoutProductivity, value.workoutId)
    }
}