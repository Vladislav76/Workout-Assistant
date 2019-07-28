package com.vladislavmyasnikov.feature_diary_impl.di

import com.vladislavmyasnikov.core_components.interfaces.ContextHolder
import com.vladislavmyasnikov.core_components.interfaces.FragmentController
import com.vladislavmyasnikov.core_components.interfaces.ScreenTitleController
import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.diary_feature_api.DiaryFeatureApi
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.DiaryEntryFragment
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.DiaryEntryListFragment
import dagger.Component

@Component(modules = [DiaryFeatureModule::class, AdapterModule::class, ViewModelFactoryModule::class, RepositoryModule::class],
        dependencies = [DiaryFeatureDependencies::class])
@PerFeature
abstract class DiaryFeatureComponent : DiaryFeatureApi {

    abstract fun inject(fragment: DiaryEntryListFragment)
    abstract fun inject(fragment: DiaryEntryFragment)

    companion object {

        @Volatile
        private var diaryFeatureComponent: DiaryFeatureComponent? = null

        fun initAndGet(dependencies: DiaryFeatureDependencies): DiaryFeatureApi {
            if (diaryFeatureComponent == null) {
                synchronized(DiaryFeatureComponent::class.java) {
                    if (diaryFeatureComponent == null) {
                        diaryFeatureComponent = DaggerDiaryFeatureComponent.builder()
                                .diaryFeatureDependencies(dependencies)
                                .build()
                    }
                }
            }
            return diaryFeatureComponent!!
        }

        fun get(): DiaryFeatureComponent {
            if (diaryFeatureComponent == null) {
                throw RuntimeException("You must call 'initAndGet' method")
            }
            return diaryFeatureComponent!!
        }
    }
}

@Component(dependencies = [ContextHolder::class, ScreenTitleController::class, FragmentController::class])
@PerFeature
interface DiaryFeatureDependenciesComponent : DiaryFeatureDependencies