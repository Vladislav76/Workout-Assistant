package com.vladislavmyasnikov.feature_diary_impl.di

import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.feature_diary_impl.data.db.LocalDatabase
import com.vladislavmyasnikov.feature_diary_impl.data.repo_mapper_impl.DiaryRepositoryImpl
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryRepository
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class])
class RepositoryModule {

    @Provides
    @PerFeature
    fun provideDiaryRepository(localDataSource: LocalDatabase): DiaryRepository = DiaryRepositoryImpl(localDataSource)
}