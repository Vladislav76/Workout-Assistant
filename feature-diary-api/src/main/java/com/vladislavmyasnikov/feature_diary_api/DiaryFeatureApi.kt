package com.vladislavmyasnikov.feature_diary_api

import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder

interface DiaryFeatureApi {

    fun diaryLauncher(): DiaryLauncher
    fun labelLibraryHolder(): LabelLibraryHolder
    fun diaryInteractor(): DiaryInteractor
}