package com.vladislavmyasnikov.feature_diary_impl.data.db

import com.vladislavmyasnikov.common.utils.Mapper
import com.vladislavmyasnikov.feature_diary_api.domain.entity.FullDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.data.db.entity.ShortDiaryEntry as ShortDiaryEntryEntity
import com.vladislavmyasnikov.feature_diary_impl.data.db.entity.FullDiaryEntry as FullDiaryEntryEntity

// DATABASE -> DOMAIN (short)
object EntityToModelShortDiaryEntryMapper : Mapper<ShortDiaryEntryEntity, ShortDiaryEntry>() {

    override fun map(value: ShortDiaryEntryEntity): ShortDiaryEntry {
        return ShortDiaryEntry(value.id, value.date, value.startTime, value.endTime, value.duration)
    }
}

// DATABASE -> DOMAIN (full)
object EntityToModelFullDiaryEntryMapper : Mapper<FullDiaryEntryEntity, FullDiaryEntry>() {

    override fun map(value: FullDiaryEntryEntity): FullDiaryEntry {
        return FullDiaryEntry(value.id, value.date, value.startTime, value.endTime, value.duration, value.description)
    }
}

// DOMAIN -> DATABASE (full)
object ModelToEntityFullDiaryEntryMapper : Mapper<FullDiaryEntry, FullDiaryEntryEntity>() {

    override fun map(value: FullDiaryEntry): FullDiaryEntryEntity {
        return FullDiaryEntryEntity(value.id, value.date, value.startTime, value.endTime, value.duration, value.description)
    }
}