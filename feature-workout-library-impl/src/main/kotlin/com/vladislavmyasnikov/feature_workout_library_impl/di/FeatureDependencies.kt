package com.vladislavmyasnikov.feature_workout_library_impl.di

import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.feature_exercise_library_api.ExerciseLibraryFeatureApi

interface FeatureDependencies {

    fun contextHolder(): ContextHolder
    fun exerciseLibraryFeatureApi(): ExerciseLibraryFeatureApi
}