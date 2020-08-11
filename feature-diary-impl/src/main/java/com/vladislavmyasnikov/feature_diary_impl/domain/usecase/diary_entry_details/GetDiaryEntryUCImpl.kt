package com.vladislavmyasnikov.feature_diary_impl.domain.usecase.diary_entry_details

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_diary_api.domain.entity.DiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.domain.repository.DiaryEntryRepository
import io.reactivex.Single
import javax.inject.Inject

@PerScreen
class GetDiaryEntryUCImpl @Inject constructor(
        private val diaryEntryRepository: DiaryEntryRepository
) : GetDiaryEntryUC {

    override fun getDiaryEntryById(id: Long): Single<DiaryEntry> = diaryEntryRepository.fetchEntryById(id)
}