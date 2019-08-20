package com.vladislavmyasnikov.feature_diary_impl.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.core_components.di.LocalNavigationModule
import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.feature_diary_impl.data.repo_mapper_impl.DiaryEntryRepositoryImpl
import com.vladislavmyasnikov.feature_diary_impl.domain.DiaryEntryRepository
import com.vladislavmyasnikov.feature_diary_impl.presentation.adapters.ShortDiaryEntryAdapter
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.FlowFragment
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.ViewModelFactory
import com.vladislavmyasnikov.features_api.diary.DiaryLauncher
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DiaryFeatureModule.Bindings::class, LocalNavigationModule::class])
class DiaryFeatureModule {

    @Provides
    @PerFeature
    fun provideViewModelFactory(repository: DiaryEntryRepository) = ViewModelFactory(repository)

    @Provides
    @PerFeature
    fun provideDiaryEntryAdapter(context: Context) = ShortDiaryEntryAdapter(context)

    @Provides
    @PerFeature
    fun provideDiaryLauncher(): DiaryLauncher = object : DiaryLauncher {
        override fun launch(): Fragment {
            return FlowFragment.newInstance()
        }
    }

    @Module(includes = [DatabaseModule::class])
    abstract class Bindings {

        @Binds
        @PerFeature
        abstract fun provideDiaryEntryRepository(impl: DiaryEntryRepositoryImpl): DiaryEntryRepository
    }
}