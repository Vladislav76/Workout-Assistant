package com.vladislavmyasnikov.workout_diary_api

import com.vladislavmyasnikov.common.arch.component.FlowLauncher
import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder

interface DiaryFeatureApi {

    fun diaryLauncher(): FlowLauncher
    fun labelLibraryHolder(): LabelLibraryHolder
}