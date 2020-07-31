package com.vladislavmyasnikov.feature_diary_impl.di.component

import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.FactoryModule
import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.common.models.SyncObject
import com.vladislavmyasnikov.feature_diary_api.DiaryFeatureApi
import com.vladislavmyasnikov.feature_diary_impl.di.component.DaggerDiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.di.FeatureDependencies
import com.vladislavmyasnikov.feature_diary_impl.di.module.FeatureModule
import com.vladislavmyasnikov.feature_diary_impl.di.module.HostFragmentBindingModule
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.DiaryFeatureFlow
import dagger.Component

@Component(
        modules = [FeatureModule::class, FactoryModule::class, HostFragmentBindingModule::class],
        dependencies = [FeatureDependencies::class]
)
@PerFeature
abstract class DiaryFeatureComponent : DiaryFeatureApi {

    abstract val fragmentFactory: FragmentFactory

    abstract fun inject(fragment: DiaryFeatureFlow)

    abstract fun screenComponent(): ScreenComponent

    val diaryEntryListComponent = SyncObject { screenComponent() }
    val diaryEntryComponent = SyncObject { screenComponent() }

    companion object {

        @Volatile
        private var featureComponent: DiaryFeatureComponent? = null

        fun initAndGet(dependencies: FeatureDependencies): DiaryFeatureApi {
            return featureComponent
                    ?: synchronized(DiaryFeatureComponent::class.java) {
                featureComponent
                        ?: DaggerDiaryFeatureComponent.builder()
                        .featureDependencies(dependencies)
                        .build()
                        .also { featureComponent = it }
            }
        }

        fun get(): DiaryFeatureComponent = featureComponent
                ?: throw RuntimeException("You must call 'initAndGet' method")
    }
}

@Component(dependencies = [ContextHolder::class])
@PerFeature
interface DiaryFeatureDependenciesComponent : FeatureDependencies