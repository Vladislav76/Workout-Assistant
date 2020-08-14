package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.LocalNavigationModule
import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder
import com.vladislavmyasnikov.feature_workout_library_api.WorkoutDiaryLauncher
import com.vladislavmyasnikov.feature_workout_library_api.WorkoutLibraryInteractor
import com.vladislavmyasnikov.feature_workout_library_api.WorkoutLibraryLauncher
import com.vladislavmyasnikov.feature_workout_library_impl.di.LabelLibraryHolderImpl
import com.vladislavmyasnikov.feature_workout_library_impl.data.repository.WorkoutRepositoryImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.repository.WorkoutRepository
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.WorkoutDiaryFlow
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.WorkoutLibraryFlow
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class, LocalNavigationModule::class, DependencyModule::class])
abstract class FeatureModule {

    @Binds @PerFeature
    abstract fun bind1(impl: WorkoutLibraryFlow): WorkoutLibraryLauncher

    @Binds @PerFeature
    abstract fun bind2(impl: WorkoutRepositoryImpl): WorkoutRepository

    @Binds @PerFeature
    abstract fun bind3(impl: WorkoutRepositoryImpl): WorkoutLibraryInteractor

    @Binds @PerFeature
    abstract fun bind4(impl: WorkoutDiaryFlow): WorkoutDiaryLauncher

    companion object {
        @Provides @PerFeature
        fun provide1(): LabelLibraryHolder = LabelLibraryHolderImpl
    }
}