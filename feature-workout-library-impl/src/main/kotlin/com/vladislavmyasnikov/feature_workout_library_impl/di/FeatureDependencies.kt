package com.vladislavmyasnikov.feature_workout_library_impl.di

import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryFeatureApi

interface FeatureDependencies {

    fun contextHolder(): ContextHolder
    fun exerciseLibraryFeatureApi(): ExerciseLibraryFeatureApi
}