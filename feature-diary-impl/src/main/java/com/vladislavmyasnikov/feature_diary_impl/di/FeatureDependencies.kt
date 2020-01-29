package com.vladislavmyasnikov.feature_diary_impl.di

import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.common.legacy.interfaces.ScreenTitleController

interface FeatureDependencies {

    fun contextHolder(): ContextHolder
    fun screenTitleController(): ScreenTitleController
}