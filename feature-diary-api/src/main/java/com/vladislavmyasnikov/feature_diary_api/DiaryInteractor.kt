package com.vladislavmyasnikov.feature_diary_api

import com.vladislavmyasnikov.feature_diary_api.domain.entity.DiaryEntry
import io.reactivex.Completable

interface DiaryInteractor {

    fun saveEntry(entry: DiaryEntry): Completable
}