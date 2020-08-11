package com.vladislavmyasnikov.feature_diary_impl.domain.usecase.diary_entry_details

import com.vladislavmyasnikov.feature_diary_api.domain.entity.DiaryEntry
import io.reactivex.Single

interface GetDiaryEntryUC {

    fun getDiaryEntryById(id: Long): Single<DiaryEntry>
}