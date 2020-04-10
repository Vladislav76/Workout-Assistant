package com.vladislavmyasnikov.feature_diary_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.LocalNavigationModule
import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder
import com.vladislavmyasnikov.feature_diary_impl.data.repo_mapper_impl.DiaryEntryRepositoryImpl
import com.vladislavmyasnikov.feature_diary_impl.di.LabelLibraryHolderImpl
import com.vladislavmyasnikov.feature_diary_impl.domain.repository.DiaryEntryRepository
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.DiaryFeatureFlow
import com.vladislavmyasnikov.features_api.diary.DiaryLauncher
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class, LocalNavigationModule::class])
abstract class FeatureModule {

    @Binds @PerFeature
    abstract fun bind1(impl: DiaryFeatureFlow): DiaryLauncher

    @Binds @PerFeature
    abstract fun bind2(impl: DiaryEntryRepositoryImpl): DiaryEntryRepository

    companion object {
        @Provides
        @PerFeature
        fun provide1(): LabelLibraryHolder = LabelLibraryHolderImpl
    }
}