package com.vladislavmyasnikov.feature_diary_impl.domain.usecase.diary_entry_list

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.domain.repository.DiaryEntryRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@PerScreen
class GetDiaryEntryListUCImpl @Inject constructor(
        private val diaryEntryRepository: DiaryEntryRepository
) : GetDiaryEntryListUC {

    override fun getAllEntries(): Observable<List<ShortDiaryEntry>> = diaryEntryRepository.fetchAllEntries()
}