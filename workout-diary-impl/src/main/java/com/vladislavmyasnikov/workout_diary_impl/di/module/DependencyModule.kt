package com.vladislavmyasnikov.workout_diary_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutLibraryFeatureApi
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutLibraryInteractor
import dagger.Module
import dagger.Provides

@Module
class DependencyModule {

    @Provides @PerFlow
    fun provide1(api: WorkoutLibraryFeatureApi): WorkoutLibraryInteractor = api.workoutLibraryInteractor()
}