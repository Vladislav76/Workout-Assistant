package com.vladislavmyasnikov.workout_diary_api

import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder

interface DiaryFeatureApi {

    fun diaryLauncher(): DiaryLauncher
    fun labelLibraryHolder(): LabelLibraryHolder
}