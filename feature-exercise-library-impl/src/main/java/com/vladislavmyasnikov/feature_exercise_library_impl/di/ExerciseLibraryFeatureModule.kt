package com.vladislavmyasnikov.feature_exercise_library_impl.di

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.LocalNavigationModule
import com.vladislavmyasnikov.feature_exercise_library_impl.data.repo_mapper_impl.ExerciseRepositoryImpl
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ExerciseRepository
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.FlowFragment
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryInteractor
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryLauncher
import dagger.Binds
import dagger.Module

@Module(includes = [DatabaseModule::class, LocalNavigationModule::class])
abstract class ExerciseLibraryFeatureModule {

    @Binds @PerFeature
    abstract fun bindFeatureLauncher(launcher: FlowFragment): ExerciseLibraryLauncher

    @Binds @PerFeature
    abstract fun provideExerciseRepository(impl: ExerciseRepositoryImpl): ExerciseRepository

    @Binds @PerFeature
    abstract fun provideExerciseLibraryInteractor(impl: ExerciseRepositoryImpl): ExerciseLibraryInteractor
}