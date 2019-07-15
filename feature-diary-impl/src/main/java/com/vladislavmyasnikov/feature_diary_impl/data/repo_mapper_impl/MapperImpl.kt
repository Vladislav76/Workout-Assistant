package com.vladislavmyasnikov.feature_diary_impl.data.repo_mapper_impl

import com.vladislavmyasnikov.core_utils.utils.utils.Mapper
import com.vladislavmyasnikov.feature_diary_impl.domain.FullDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.domain.ShortDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.data.db.entities.ShortDiaryEntry as ShortDiaryEntryEntity
import com.vladislavmyasnikov.feature_diary_impl.data.db.entities.FullDiaryEntry as FullDiaryEntryEntity

object EntityToModelShortDiaryEntryMapper : Mapper<ShortDiaryEntryEntity, ShortDiaryEntry>() {

    override fun map(value: ShortDiaryEntryEntity): ShortDiaryEntry = ShortDiaryEntry(value.id, value.date, value.duration)
}

object EntityToModelFullDiaryEntryMapper : Mapper<FullDiaryEntryEntity, FullDiaryEntry>() {

    override fun map(value: FullDiaryEntryEntity): FullDiaryEntry = FullDiaryEntry(value.id, value.date, value.duration, value.startTime, value.endTime, value.description)
}