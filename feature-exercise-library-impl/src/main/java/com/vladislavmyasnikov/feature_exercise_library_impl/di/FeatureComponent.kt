package com.vladislavmyasnikov.feature_exercise_library_impl.di

import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.FactoryModule
import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.common.models.SyncObject
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.FeatureFlowFragment
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryFeatureApi
import dagger.Component

@Component(
        modules = [FeatureModule::class, FactoryModule::class, HostFragmentBindingModule::class],
        dependencies = [FeatureDependencies::class]
)
@PerFeature
abstract class FeatureComponent : ExerciseLibraryFeatureApi {

    abstract val fragmentFactory: FragmentFactory

    abstract fun inject(fragment: FeatureFlowFragment)

    abstract fun screenComponent(): ScreenComponent

    val exerciseLibraryComponent = SyncObject { screenComponent() }
    val exerciseDetailsComponent = SyncObject { screenComponent() }

    companion object {

        @Volatile
        private var featureComponent: FeatureComponent? = null

        fun initAndGet(dependencies: FeatureDependencies): ExerciseLibraryFeatureApi {
            return featureComponent ?: synchronized(FeatureComponent::class.java) {
                featureComponent ?: DaggerFeatureComponent.builder()
                        .featureDependencies(dependencies)
                        .build()
                        .also { featureComponent = it }
            }
        }

        fun get(): FeatureComponent = featureComponent ?: throw RuntimeException("You must call 'initAndGet' method")
    }
}

@Component(dependencies = [ContextHolder::class])
@PerFeature
interface FeatureDependenciesComponent : FeatureDependencies