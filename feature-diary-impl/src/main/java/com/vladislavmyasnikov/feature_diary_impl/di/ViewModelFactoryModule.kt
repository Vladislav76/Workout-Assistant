package com.vladislavmyasnikov.feature_diary_impl.di

import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryRepository
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.DiaryEntryListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule {

    @Provides
    @PerFeature
    fun provideDiaryEntryListViewModelFactory(repository: DiaryRepository) = DiaryEntryListViewModelFactory(repository)
}