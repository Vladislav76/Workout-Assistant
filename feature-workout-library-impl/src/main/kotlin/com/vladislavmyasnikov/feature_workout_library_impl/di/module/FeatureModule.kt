package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.LocalNavigationModule
import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder
import com.vladislavmyasnikov.feature_workout_library_impl.di.LabelLibraryHolderImpl
import com.vladislavmyasnikov.feature_workout_library_impl.data.repo_mapper_impl.WorkoutRepositoryImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.repository.WorkoutRepository
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.WorkoutLibraryFeatureFlow
import com.vladislavmyasnikov.features_api.workout_library.WorkoutLibraryLauncher
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class, LocalNavigationModule::class, DependencyModule::class])
abstract class FeatureModule {

    @Binds @PerFeature
    abstract fun bind1(impl: WorkoutLibraryFeatureFlow): WorkoutLibraryLauncher

    @Binds @PerFeature
    abstract fun bind2(impl: WorkoutRepositoryImpl): WorkoutRepository

    companion object {
        @Provides @PerFeature
        fun provide1(): LabelLibraryHolder = LabelLibraryHolderImpl
    }
}