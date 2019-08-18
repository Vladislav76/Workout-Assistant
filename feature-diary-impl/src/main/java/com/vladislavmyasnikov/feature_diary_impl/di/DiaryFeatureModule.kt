package com.vladislavmyasnikov.feature_diary_impl.di

import android.content.Context
import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.diary_feature_api.DiaryLauncher
import com.vladislavmyasnikov.feature_diary_impl.api_impl.DiaryLauncherImpl
import com.vladislavmyasnikov.feature_diary_impl.data.repo_mapper_impl.DiaryEntryRepositoryImpl
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryEntryRepository
import com.vladislavmyasnikov.feature_diary_impl.presentation.adapters.ShortDiaryEntryAdapter
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.DiaryEntryListViewModel
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.DiaryEntryViewModel
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DiaryFeatureModule.Bindings::class])
class DiaryFeatureModule {

    @Provides
    @PerFeature
    fun provideViewModelFactory(repository: DiaryEntryRepository) = ViewModelFactory(repository)

    @Provides
    @PerFeature
    fun provideDiaryEntryAdapter(context: Context) = ShortDiaryEntryAdapter(context)

    @Module(includes = [DatabaseModule::class])
    abstract class Bindings {

        @PerFeature
        @Binds
        abstract fun provideDiaryLauncher(impl: DiaryLauncherImpl): DiaryLauncher

        @PerFeature
        @Binds
        abstract fun provideDiaryEntryRepository(impl: DiaryEntryRepositoryImpl): DiaryEntryRepository
    }
}