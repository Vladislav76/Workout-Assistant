package com.vladislavmyasnikov.feature_diary_impl.di

import com.vladislavmyasnikov.common.interfaces.ContextHolder

interface FeatureDependencies {

    fun contextHolder(): ContextHolder
}