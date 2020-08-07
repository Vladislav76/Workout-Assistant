package com.vladislavmyasnikov.feature_workout_library_impl.di.component

import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.FactoryModule
import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.common.models.SyncObject
import com.vladislavmyasnikov.feature_diary_api.DiaryFeatureApi
import com.vladislavmyasnikov.feature_workout_library_impl.di.FeatureDependencies
import com.vladislavmyasnikov.feature_workout_library_impl.di.module.FeatureModule
import com.vladislavmyasnikov.feature_workout_library_impl.di.module.HostFragmentBindingModule
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.WorkoutLibraryFeatureFlow
import com.vladislavmyasnikov.feature_exercise_library_api.ExerciseLibraryFeatureApi
import com.vladislavmyasnikov.feature_workout_library_api.WorkoutLibraryFeatureApi
import dagger.Component

@Component(
        modules = [FeatureModule::class, FactoryModule::class, HostFragmentBindingModule::class],
        dependencies = [FeatureDependencies::class]
)
@PerFeature
abstract class WorkoutLibraryFeatureComponent : WorkoutLibraryFeatureApi {

    abstract val fragmentFactory: FragmentFactory

    abstract fun inject(fragment: WorkoutLibraryFeatureFlow)

    abstract fun screenComponent(): ScreenComponent

    val workoutListComponent = SyncObject { screenComponent() }
    val workoutDetailsComponent = SyncObject { screenComponent() }
    val workoutPlayerComponent = SyncObject { screenComponent() }
    val workoutResultComponent = SyncObject { screenComponent() }

    companion object {

        @Volatile
        private var featureComponent: WorkoutLibraryFeatureComponent? = null

        fun initAndGet(dependencies: FeatureDependencies): WorkoutLibraryFeatureApi {
            return featureComponent
                    ?: synchronized(WorkoutLibraryFeatureComponent::class.java) {
                featureComponent ?: DaggerWorkoutLibraryFeatureComponent.builder()
                        .featureDependencies(dependencies)
                        .build()
                        .also { featureComponent = it }
            }
        }

        fun get(): WorkoutLibraryFeatureComponent = featureComponent
                ?: throw RuntimeException("You must call 'initAndGet' method")
    }
}

@Component(dependencies = [ContextHolder::class, ExerciseLibraryFeatureApi::class, DiaryFeatureApi::class])
@PerFeature
interface WorkoutLibraryFeatureDependenciesComponent : FeatureDependencies