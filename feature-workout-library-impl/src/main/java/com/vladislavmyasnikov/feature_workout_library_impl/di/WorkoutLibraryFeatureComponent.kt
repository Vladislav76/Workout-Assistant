package com.vladislavmyasnikov.feature_workout_library_impl.di

import com.vladislavmyasnikov.common.di.PerFeature
import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.common.interfaces.ScreenTitleController
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.FlowFragment
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.WorkoutListFragment
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryFeatureApi
import com.vladislavmyasnikov.features_api.workout_library.WorkoutLibraryFeatureApi
import dagger.Component

@Component(modules = [WorkoutLibraryFeatureModule::class], dependencies = [WorkoutLibraryFeatureDependencies::class])
@PerFeature
abstract class WorkoutLibraryFeatureComponent : WorkoutLibraryFeatureApi {

    abstract fun inject(fragment: FlowFragment)
    abstract fun inject(fragment: WorkoutListFragment)

    companion object {

        @Volatile
        private var featureComponent: WorkoutLibraryFeatureComponent? = null

        fun initAndGet(dependencies: WorkoutLibraryFeatureDependencies): WorkoutLibraryFeatureApi {
            return featureComponent ?: synchronized(WorkoutLibraryFeatureComponent::class.java) {
                featureComponent ?: DaggerWorkoutLibraryFeatureComponent.builder()
                        .workoutLibraryFeatureDependencies(dependencies)
                        .build()
                        .also { featureComponent = it }
            }
        }

        fun get(): WorkoutLibraryFeatureComponent = featureComponent ?: throw RuntimeException("You must call 'initAndGet' method")
    }
}

@Component(dependencies = [ContextHolder::class, ScreenTitleController::class, ExerciseLibraryFeatureApi::class])
@PerFeature
interface WorkoutLibraryFeatureDependenciesComponent : WorkoutLibraryFeatureDependencies