package com.vladislavmyasnikov.workout_library_and_player_impl.di.component

import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.common.di.modules.FactoryModule
import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.common.models.SyncObject
import com.vladislavmyasnikov.exercise_library_api.ExerciseLibraryFeatureApi
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutLibraryFeatureApi
import com.vladislavmyasnikov.workout_library_and_player_impl.di.FeatureDependencies
import com.vladislavmyasnikov.workout_library_and_player_impl.di.module.FeatureModule
import com.vladislavmyasnikov.workout_library_and_player_impl.di.module.HostFragmentBindingModule
import com.vladislavmyasnikov.workout_library_and_player_impl.di.module.UCBindingModule
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.WorkoutCreationFlow
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.WorkoutLibraryFlow
import dagger.Component

@Component(
        modules = [FeatureModule::class, FactoryModule::class, HostFragmentBindingModule::class, UCBindingModule::class],
        dependencies = [FeatureDependencies::class]
)
@PerFlow
abstract class WorkoutLibraryFeatureComponent : WorkoutLibraryFeatureApi {

    abstract val fragmentFactory: FragmentFactory

    abstract fun inject(fragment: WorkoutLibraryFlow)
    abstract fun inject(fragment: WorkoutCreationFlow)

    abstract fun screenComponent(): ScreenComponent

    val workoutListComponent = SyncObject { screenComponent() }
    val workoutDetailsComponent = SyncObject { screenComponent() }
    val workoutPlayerComponent = SyncObject { screenComponent() }
    val workoutResultComponent = SyncObject { screenComponent() }
    val workoutSetListComponent = SyncObject { screenComponent() } // delete
    val workoutExerciseListComponent = SyncObject { screenComponent() } // delete
    val workoutExerciseCycleListComponent = SyncObject { screenComponent() } // delete

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

@Component(dependencies = [ContextHolder::class, ExerciseLibraryFeatureApi::class])
@PerFlow
interface WorkoutLibraryFeatureDependenciesComponent : FeatureDependencies