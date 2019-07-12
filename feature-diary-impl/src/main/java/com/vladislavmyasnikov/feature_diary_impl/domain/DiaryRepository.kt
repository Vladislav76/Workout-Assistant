package com.vladislavmyasnikov.feature_diary_impl.domain

import io.reactivex.Maybe

interface DiaryRepository {

    fun fetchEntries(): Maybe<List<DiaryEntry>>
}