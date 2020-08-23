package com.vladislavmyasnikov.exercise_library_impl.di.component

import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.FactoryModule
import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.common.models.SyncObject
import com.vladislavmyasnikov.exercise_library_api.ExerciseLibraryFeatureApi
import com.vladislavmyasnikov.exercise_library_impl.di.FeatureDependencies
import com.vladislavmyasnikov.exercise_library_impl.di.module.FeatureModule
import com.vladislavmyasnikov.exercise_library_impl.di.module.HostFragmentBindingModule
import com.vladislavmyasnikov.exercise_library_impl.presentation.ExerciseLibraryFlow
import dagger.Component

@Component(
        modules = [FeatureModule::class, FactoryModule::class, HostFragmentBindingModule::class],
        dependencies = [FeatureDependencies::class]
)
@PerFeature
abstract class ExerciseLibraryFeatureComponent : ExerciseLibraryFeatureApi {

    abstract val fragmentFactory: FragmentFactory

    abstract fun inject(fragment: ExerciseLibraryFlow)

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