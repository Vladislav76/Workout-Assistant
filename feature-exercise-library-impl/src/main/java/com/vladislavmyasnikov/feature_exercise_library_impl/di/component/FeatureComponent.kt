package com.vladislavmyasnikov.feature_exercise_library_impl.di.component

import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.FactoryModule
import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.common.models.SyncObject
import com.vladislavmyasnikov.feature_exercise_library_impl.di.component.DaggerExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.di.FeatureDependencies
import com.vladislavmyasnikov.feature_exercise_library_impl.di.module.FeatureModule
import com.vladislavmyasnikov.feature_exercise_library_impl.di.module.HostFragmentBindingModule
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.ExerciseLibraryFeatureFlow
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryFeatureApi
import dagger.Component

@Component(
        modules = [FeatureModule::class, FactoryModule::class, HostFragmentBindingModule::class],
        dependencies = [FeatureDependencies::class]
)
@PerFeature
abstract class ExerciseLibraryFeatureComponent : ExerciseLibraryFeatureApi {

    abstract val fragmentFactory: FragmentFactory

    abstract fun inject(fragment: ExerciseLibraryFeatureFlow)

    abstract fun screenComponent(): ScreenComponent

    val exerciseListComponent = SyncObject { screenComponent() }
    val exerciseDetailsComponent = SyncObject { screenComponent() }

    companion object {

        @Volatile
        private var featureComponent: ExerciseLibraryFeatureComponent? = null

        fun initAndGet(dependencies: FeatureDependencies): ExerciseLibraryFeatureApi {
            return featureComponent
                    ?: synchronized(ExerciseLibraryFeatureComponent::class.java) {
                featureComponent
                        ?: DaggerExerciseLibraryFeatureComponent.builder()
                        .featureDependencies(dependencies)
                        .build()
                        .also { featureComponent = it }
            }
        }

        fun get(): ExerciseLibraryFeatureComponent = featureComponent
                ?: throw RuntimeException("You must call 'initAndGet' method")
    }
}

@Component(dependencies = [ContextHolder::class])
@PerFeature
interface ExerciseLibraryFeatureDependenciesComponent : FeatureDependencies