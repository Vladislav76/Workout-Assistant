package com.vladislavmyasnikov.workout_library_and_player_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.exercise_library_api.ExerciseLibraryFeatureApi
import dagger.Module
import dagger.Provides

@Module
class DependencyModule {

    @Provides @PerFlow
    fun provide1(api: ExerciseLibraryFeatureApi) = api.exerciseLibraryInteractor()
}