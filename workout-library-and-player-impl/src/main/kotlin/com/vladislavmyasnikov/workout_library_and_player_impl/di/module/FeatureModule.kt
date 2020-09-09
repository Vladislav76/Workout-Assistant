package com.vladislavmyasnikov.workout_library_and_player_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.common.di.modules.LocalNavigationModule
import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder
import com.vladislavmyasnikov.workout_library_and_player_api.*
import com.vladislavmyasnikov.workout_library_and_player_impl.data.repository.WorkoutRepositoryImpl
import com.vladislavmyasnikov.workout_library_and_player_impl.di.LabelLibraryHolderImpl
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.repository.WorkoutRepository
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.WorkoutCreatorFlow
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.WorkoutPlayerFlow
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.WorkoutLibraryFlow
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class, LocalNavigationModule::class, DependencyModule::class])
abstract class FeatureModule {

    @Binds @PerFlow
    abstract fun bind1(impl: WorkoutRepositoryImpl): WorkoutRepository

    @Binds @PerFlow
    abstract fun bind2(impl: WorkoutRepositoryImpl): WorkoutLibraryInteractor

    @Binds
    abstract fun bind3(impl: WorkoutLibraryFlow.Launcher): WorkoutLibraryFlowLauncher

    @Binds
    abstract fun bind4(impl: WorkoutPlayerFlow.Launcher): WorkoutPlayerFlowLauncher

    @Binds
    abstract fun bind5(impl: WorkoutCreatorFlow.Launcher): WorkoutCreatorFlowLauncher

    companion object {
        @Provides @PerFlow
        fun provide1(): LabelLibraryHolder = LabelLibraryHolderImpl
    }
}