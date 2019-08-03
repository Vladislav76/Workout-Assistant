package com.vladislavmyasnikov.feature_diary_impl.di

import android.content.Context
import com.vladislavmyasnikov.core_components.di.ContextModule
import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.feature_diary_impl.presentation.adapters.ShortDiaryEntryAdapter
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class AdapterModule {

    @Provides
    @PerFeature
    fun provideDiaryEntryAdapter(context: Context): ShortDiaryEntryAdapter = ShortDiaryEntryAdapter(context)
}