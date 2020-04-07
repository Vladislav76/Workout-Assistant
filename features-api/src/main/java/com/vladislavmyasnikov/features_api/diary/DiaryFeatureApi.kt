package com.vladislavmyasnikov.features_api.diary

import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder

interface DiaryFeatureApi {

    fun diaryLauncher(): DiaryLauncher
    fun labelLibraryHolder(): LabelLibraryHolder
//    fun diaryInteractor(): DiaryInteractor
}