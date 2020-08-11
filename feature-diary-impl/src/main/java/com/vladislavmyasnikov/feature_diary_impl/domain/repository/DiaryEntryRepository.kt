package com.vladislavmyasnikov.feature_diary_impl.domain.repository

import com.vladislavmyasnikov.feature_diary_api.domain.entity.DiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface DiaryEntryRepository {

    fun fetchAllEntries(): Observable<List<ShortDiaryEntry>>
    fun fetchEntryById(id: Long): Single<DiaryEntry>
    fun saveEntry(entry: DiaryEntry): Completable
    fun deleteEntriesByIds(ids: List<Long>): Completable
}