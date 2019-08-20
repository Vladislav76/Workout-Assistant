package com.vladislavmyasnikov.feature_diary_impl.di

import com.vladislavmyasnikov.core_components.interfaces.ContextHolder
import com.vladislavmyasnikov.core_components.interfaces.ScreenTitleController

interface DiaryFeatureDependencies {

    fun contextHolder(): ContextHolder
    fun screenTitleController(): ScreenTitleController
}