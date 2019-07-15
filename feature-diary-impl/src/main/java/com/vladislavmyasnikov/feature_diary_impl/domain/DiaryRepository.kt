package com.vladislavmyasnikov.feature_diary_impl.domain

import io.reactivex.Observable

interface DiaryRepository {

    fun fetchShortEntries(): Observable<List<ShortDiaryEntry>>
}