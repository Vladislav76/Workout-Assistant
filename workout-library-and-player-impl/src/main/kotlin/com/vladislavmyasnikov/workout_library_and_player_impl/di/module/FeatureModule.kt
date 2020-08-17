package com.vladislavmyasnikov.workout_library_and_player_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.modules.LocalNavigationModule
import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutLibraryInteractor
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutLibraryLauncher
import com.vladislavmyasnikov.workout_library_and_player_impl.di.LabelLibraryHolderImpl
import com.vladislavmyasnikov.workout_library_and_player_impl.data.repository.WorkoutRepositoryImpl
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.repository.WorkoutRepository
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.WorkoutLibraryFlow
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

    companion object {
        @Provides @PerFeature
        fun provide1(): LabelLibraryHolder = LabelLibraryHolderImpl
    }
}