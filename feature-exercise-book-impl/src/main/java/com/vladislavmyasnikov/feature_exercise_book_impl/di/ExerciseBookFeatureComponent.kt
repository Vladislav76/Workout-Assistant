package com.vladislavmyasnikov.feature_exercise_book_impl.di

import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.core_components.interfaces.ContextHolder
import com.vladislavmyasnikov.core_components.interfaces.FragmentController
import com.vladislavmyasnikov.core_components.interfaces.ScreenTitleController
import com.vladislavmyasnikov.feature_exercise_book_api.ExerciseBookFeatureApi
import dagger.Component

@Component(modules = [ExerciseBookFeatureModule::class], dependencies = [ExerciseBookFeatureDependencies::class])
@PerFeature
abstract class ExerciseBookFeatureComponent : ExerciseBookFeatureApi {

    abstract fun exerciseBookScreenComponent(module: AdapterModule): ExerciseBookScreenComponent

    companion object {

        @Volatile
        private var featureComponent: ExerciseBookFeatureComponent? = null

        fun initAndGet(dependencies: ExerciseBookFeatureDependencies): ExerciseBookFeatureApi {
            return featureComponent ?: synchronized(ExerciseBookFeatureComponent::class.java) {
                featureComponent ?: DaggerExerciseBookFeatureComponent.builder()
                        .exerciseBookFeatureDependencies(dependencies)
                        .build()
                        .also { featureComponent = it }
            }
        }

        fun get(): ExerciseBookFeatureComponent = featureComponent ?: throw RuntimeException("You must call 'initAndGet' method")
    }
}

@Component(dependencies = [ContextHolder::class, ScreenTitleController::class, FragmentController::class])
@PerFeature
interface ExerciseBookFeatureDependenciesComponent : ExerciseBookFeatureDependencies