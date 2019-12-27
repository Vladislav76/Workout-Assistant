package com.vladislavmyasnikov.feature_diary_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.common.utils.Mapper
import com.vladislavmyasnikov.feature_diary_impl.domain.FullDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.domain.ShortDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.data.db.entities.ShortDiaryEntry as ShortDiaryEntryEntity
import com.vladislavmyasnikov.feature_diary_impl.data.db.entities.FullDiaryEntry as FullDiaryEntryEntity

// Short Entity -> Model
object EntityToModelShortDiaryEntryMapper : Mapper<ShortDiaryEntryEntity, ShortDiaryEntry>() {

    override fun map(value: ShortDiaryEntryEntity): ShortDiaryEntry {
        return ShortDiaryEntry(value.id, value.date, value.startTime, value.endTime, value.duration)
    }
}

// Full Entity -> Model
object EntityToModelFullDiaryEntryMapper : Mapper<FullDiaryEntryEntity, FullDiaryEntry>() {

    override fun map(value: FullDiaryEntryEntity): FullDiaryEntry {
        return FullDiaryEntry(value.id, value.date, value.startTime, value.endTime, value.duration, value.description)
    }
}

// Model -> Full Entity
object ModelToEntityFullDiaryEntryMapper : Mapper<FullDiaryEntry, FullDiaryEntryEntity>() {

    override fun map(value: FullDiaryEntry): FullDiaryEntryEntity {
        return FullDiaryEntryEntity(value.id, value.date, value.startTime, value.endTime, value.duration, value.description)
    }
}