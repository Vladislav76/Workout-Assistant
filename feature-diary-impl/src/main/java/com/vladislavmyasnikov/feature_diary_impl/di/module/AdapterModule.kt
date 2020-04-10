package com.vladislavmyasnikov.feature_diary_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_diary_impl.presentation.adapters.DiaryEntryAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides @PerScreen
    fun provide1() = DiaryEntryAdapter()
}