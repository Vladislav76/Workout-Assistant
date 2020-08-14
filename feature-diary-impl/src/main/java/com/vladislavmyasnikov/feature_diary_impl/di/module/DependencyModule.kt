package com.vladislavmyasnikov.feature_diary_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.feature_workout_library_api.WorkoutLibraryFeatureApi
import com.vladislavmyasnikov.feature_workout_library_api.WorkoutLibraryInteractor
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class DependencyModule {

    @Provides @PerFeature
    fun provide1(api: WorkoutLibraryFeatureApi): WorkoutLibraryInteractor = api.workoutLibraryInteractor()
}