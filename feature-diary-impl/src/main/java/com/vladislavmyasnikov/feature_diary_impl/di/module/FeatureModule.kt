package com.vladislavmyasnikov.feature_diary_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.LocalNavigationModule
import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder
import com.vladislavmyasnikov.feature_diary_api.DiaryInteractor
import com.vladislavmyasnikov.feature_diary_api.DiaryLauncher
import com.vladislavmyasnikov.feature_diary_impl.data.repository.DiaryEntryRepositoryImpl
import com.vladislavmyasnikov.feature_diary_impl.di.LabelLibraryHolderImpl
import com.vladislavmyasnikov.feature_diary_impl.domain.repository.DiaryEntryRepository
import com.vladislavmyasnikov.feature_diary_impl.presentation.DiaryFeatureFlow
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class, LocalNavigationModule::class])
abstract class FeatureModule {

    @Binds @PerFeature
    abstract fun bind1(impl: DiaryFeatureFlow): DiaryLauncher

    @Binds @PerFeature
    abstract fun bind2(impl: DiaryEntryRepositoryImpl): DiaryEntryRepository

    @Binds @PerFeature
    abstract fun bind3(impl: DiaryEntryRepositoryImpl): DiaryInteractor

    companion object {
        @Provides
        @PerFeature
        fun provide1(): LabelLibraryHolder = LabelLibraryHolderImpl
    }
}