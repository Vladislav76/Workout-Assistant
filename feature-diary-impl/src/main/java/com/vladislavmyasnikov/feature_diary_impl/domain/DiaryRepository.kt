package com.vladislavmyasnikov.feature_diary_impl.domain

import io.reactivex.Maybe
import io.reactivex.Observable

interface DiaryRepository {

    fun fetchShortEntries(): Observable<List<ShortDiaryEntry>>
    fun fetchFullEntry(id: Long): Maybe<FullDiaryEntry>
}