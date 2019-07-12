package com.vladislavmyasnikov.feature_diary_impl.data.repositories_impl

import com.vladislavmyasnikov.core_utils.utils.utils.Mapper
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.data.db.DiaryEntry as DiaryEntryEntity

object EntityToModelDiaryEntryMapper : Mapper<DiaryEntryEntity, DiaryEntry>() {

    override fun map(value: DiaryEntryEntity): DiaryEntry = DiaryEntry(value.id)
}