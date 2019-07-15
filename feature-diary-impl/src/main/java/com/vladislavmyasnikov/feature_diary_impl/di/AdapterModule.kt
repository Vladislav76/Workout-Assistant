package com.vladislavmyasnikov.feature_diary_impl.di

import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.feature_diary_impl.presentation.adapters.ShortDiaryEntryAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides
    @PerFeature
    fun provideDiaryEntryAdapter(): ShortDiaryEntryAdapter = ShortDiaryEntryAdapter()
}