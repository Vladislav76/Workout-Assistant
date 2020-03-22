package com.vladislavmyasnikov.features_api.diary

import io.reactivex.Completable

interface DiaryInteractor {

    fun saveEntry(entry: DiaryEntry): Completable
}