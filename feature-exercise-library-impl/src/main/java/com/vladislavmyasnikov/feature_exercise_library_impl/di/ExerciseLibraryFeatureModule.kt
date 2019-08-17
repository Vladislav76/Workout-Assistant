package com.vladislavmyasnikov.feature_exercise_library_impl.di

import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.feature_exercise_library_api.ExerciseLibraryLauncher
import com.vladislavmyasnikov.feature_exercise_library_impl.api_impl.ExerciseLibraryLauncherImpl
import com.vladislavmyasnikov.feature_exercise_library_impl.data.repo_mapper_impl.ExerciseRepositoryImpl
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [ExerciseLibraryFeatureModule.Bindings::class])
class ExerciseLibraryFeatureModule {

    @PerFeature
    @Provides
    fun provideViewModelFactory(repository: ExerciseRepository): ViewModelFactory = ViewModelFactory(repository)

    @Module(includes = [DatabaseModule::class])
    abstract class Bindings {

        @PerFeature
        @Binds
        abstract fun provideExerciseLibraryLauncher(impl: ExerciseLibraryLauncherImpl): ExerciseLibraryLauncher

        @PerFeature
        @Binds
        abstract fun provideExerciseRepository(impl: ExerciseRepositoryImpl): ExerciseRepository
    }
}