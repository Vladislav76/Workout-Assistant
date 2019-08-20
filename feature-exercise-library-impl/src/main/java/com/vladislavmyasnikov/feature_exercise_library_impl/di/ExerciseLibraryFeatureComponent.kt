package com.vladislavmyasnikov.feature_exercise_library_impl.di

import com.vladislavmyasnikov.core_components.components.SyncObject
import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.core_components.interfaces.ContextHolder
import com.vladislavmyasnikov.core_components.interfaces.ScreenTitleController
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryFeatureApi
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.FlowFragment
import dagger.Component

@Component(modules = [ExerciseLibraryFeatureModule::class], dependencies = [ExerciseLibraryFeatureDependencies::class])
@PerFeature
abstract class ExerciseLibraryFeatureComponent : ExerciseLibraryFeatureApi {

    val exerciseScreenComponent = SyncObject { exerciseLibraryScreenComponent() }
    val exerciseListScreenComponent = SyncObject { exerciseLibraryScreenComponent() }

    abstract fun exerciseLibraryScreenComponent(): ExerciseLibraryScreenComponent

    abstract fun inject(fragment: FlowFragment)

    companion object {

        @Volatile
        private var featureComponent: ExerciseLibraryFeatureComponent? = null

        fun initAndGet(dependencies: ExerciseLibraryFeatureDependencies): ExerciseLibraryFeatureApi {
            return featureComponent ?: synchronized(ExerciseLibraryFeatureComponent::class.java) {
                featureComponent ?: DaggerExerciseLibraryFeatureComponent.builder()
                        .exerciseLibraryFeatureDependencies(dependencies)
                        .build()
                        .also { featureComponent = it }
            }
        }

        fun get(): ExerciseLibraryFeatureComponent = featureComponent ?: throw RuntimeException("You must call 'initAndGet' method")
    }
}

@Component(dependencies = [ContextHolder::class, ScreenTitleController::class])
@PerFeature
interface ExerciseLibraryFeatureDependenciesComponent : ExerciseLibraryFeatureDependencies