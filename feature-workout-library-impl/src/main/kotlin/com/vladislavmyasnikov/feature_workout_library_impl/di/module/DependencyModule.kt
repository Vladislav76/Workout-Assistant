package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryFeatureApi
import dagger.Module
import dagger.Provides

@Module
class DependencyModule {

    @Provides @PerFeature
    fun provide1(api: ExerciseLibraryFeatureApi) = api.exerciseLibraryInteractor()
}