package com.vladislavmyasnikov.feature_diary_impl.di

import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.diary_feature_api.DiaryLauncher
import com.vladislavmyasnikov.feature_diary_impl.api_impl.DiaryLauncherImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DiaryFeatureModule {

    @PerFeature
    @Binds
    abstract fun provideDiaryLauncher(impl: DiaryLauncherImpl): DiaryLauncher
}