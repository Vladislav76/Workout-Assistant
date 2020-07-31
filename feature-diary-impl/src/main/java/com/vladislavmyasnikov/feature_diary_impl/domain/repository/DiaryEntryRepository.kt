package com.vladislavmyasnikov.feature_diary_impl.domain.repository

import com.vladislavmyasnikov.feature_diary_api.domain.entity.FullDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

interface DiaryEntryRepository {

    fun fetchShortEntries(): Observable<List<ShortDiaryEntry>>
    fun fetchFullEntry(id: Long): Maybe<FullDiaryEntry>
    fun saveFullEntry(entry: FullDiaryEntry): Completable
    fun deleteEntriesByIDs(ids: List<Long>): Completable
}