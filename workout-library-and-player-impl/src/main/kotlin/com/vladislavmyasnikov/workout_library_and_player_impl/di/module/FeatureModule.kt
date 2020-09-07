package com.vladislavmyasnikov.workout_library_and_player_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.common.di.modules.LocalNavigationModule
import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutCreationLauncher
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutExecutionLauncher
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutLibraryInteractor
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutLibraryLauncher
import com.vladislavmyasnikov.workout_library_and_player_impl.data.repository.WorkoutRepositoryImpl
import com.vladislavmyasnikov.workout_library_and_player_impl.di.LabelLibraryHolderImpl
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.repository.WorkoutRepository
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.WorkoutCreationFlow
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_execution.WorkoutExecutionFlow
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.WorkoutLibraryFlow
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class, LocalNavigationModule::class, DependencyModule::class])
abstract class FeatureModule {

    @Binds @PerFlow
    abstract fun bind1(impl: WorkoutLibraryFlow): WorkoutLibraryLauncher

    @Binds @PerFlow
    abstract fun bind2(impl: WorkoutRepositoryImpl): WorkoutRepository

    @Binds @PerFlow
    abstract fun bind3(impl: WorkoutRepositoryImpl): WorkoutLibraryInteractor

    @Binds @PerFlow
    abstract fun bind4(impl: WorkoutCreationFlow): WorkoutCreationLauncher

    @Binds @PerFlow
    abstract fun bind5(impl: WorkoutExecutionFlow): WorkoutExecutionLauncher

    companion object {
        @Provides @PerFlow
        fun provide1(): LabelLibraryHolder = LabelLibraryHolderImpl
    }
}