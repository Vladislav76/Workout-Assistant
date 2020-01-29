package com.vladislavmyasnikov.feature_exercise_library_impl.di

import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.FactoryModule
import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.common.models.SyncObject
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.ExerciseLibraryFeatureFlow
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryFeatureApi
import dagger.Component

@Component(
        modules = [FeatureModule::class, FactoryModule::class, HostFragmentBindingModule::class],
        dependencies = [FeatureDependencies::class]
)
@PerFeature
abstract class ExerciseFeatureComponent : ExerciseLibraryFeatureApi {

    abstract val fragmentFactory: FragmentFactory

    abstract fun inject(fragment: ExerciseLibraryFeatureFlow)

    abstract fun screenComponent(): ScreenComponent

    val exerciseLibraryComponent = SyncObject { screenComponent() }
    val exerciseDetailsComponent = SyncObject { screenComponent() }

    companion object {

        @Volatile
        private var featureComponent: ExerciseFeatureComponent? = null

        fun initAndGet(dependencies: FeatureDependencies): ExerciseLibraryFeatureApi {
            return featureComponent ?: synchronized(ExerciseFeatureComponent::class.java) {
                featureComponent ?: DaggerExerciseFeatureComponent.builder()
                        .featureDependencies(dependencies)
                        .build()
                        .also { featureComponent = it }
            }
        }

        fun get(): ExerciseFeatureComponent = featureComponent ?: throw RuntimeException("You must call 'initAndGet' method")
    }
}

@Component(dependencies = [ContextHolder::class])
@PerFeature
interface ExerciseFeatureDependenciesComponent : FeatureDependencies