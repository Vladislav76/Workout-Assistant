package com.vladislavmyasnikov.feature_diary_impl.domain.usecase.diary_entry_list

import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry
import io.reactivex.Observable
import io.reactivex.Single

interface GetDiaryEntryListUC {

    fun getAllEntries(): Observable<List<ShortDiaryEntry>>
}