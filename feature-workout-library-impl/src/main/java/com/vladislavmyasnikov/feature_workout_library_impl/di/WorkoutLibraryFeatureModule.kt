package com.vladislavmyasnikov.feature_workout_library_impl.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.LocalNavigationModule
import com.vladislavmyasnikov.common.di.PerFeature
import com.vladislavmyasnikov.common.di.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.data.repo_mapper_impl.WorkoutRepositoryImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutRepository
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters.WorkoutAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters.WorkoutSetAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.FlowFragment
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels.ViewModelFactory
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryFeatureApi
import com.vladislavmyasnikov.features_api.workout_library.WorkoutLibraryLauncher
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [WorkoutLibraryFeatureModule.Bindings::class, LocalNavigationModule::class])
class WorkoutLibraryFeatureModule {

    @Provides
    @PerFeature
    fun provideViewModelFactory(repository: WorkoutRepository) = ViewModelFactory(repository)

    @Provides
    @PerFeature
    fun provideWorkoutAdapter(context: Context) = WorkoutAdapter(context)

    @Provides
    @PerFeature
    fun provideWorkoutLibraryLauncher() = object : WorkoutLibraryLauncher {
        override fun launch(): Fragment {
            return FlowFragment.newInstance()
        }
    }

    @Provides
    @PerFeature
    fun provideExerciseLibraryInteractor(api: ExerciseLibraryFeatureApi) = api.exerciseLibraryInteractor()

    @Module(includes = [DatabaseModule::class])
    abstract class Bindings {

        @Binds
        @PerFeature
        abstract fun provideWorkoutRepository(impl: WorkoutRepositoryImpl): WorkoutRepository
    }
}