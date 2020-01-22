package com.vladislavmyasnikov.feature_exercise_library_impl.di

import com.vladislavmyasnikov.common.interfaces.ContextHolder

interface FeatureDependencies {

    fun contextHolder(): ContextHolder
}