package com.vladislavmyasnikov.feature_workout_library_impl.di

import com.vladislavmyasnikov.core_components.interfaces.ContextHolder
import com.vladislavmyasnikov.core_components.interfaces.ScreenTitleController
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryFeatureApi

interface WorkoutLibraryFeatureDependencies {

    fun contextHolder(): ContextHolder
    fun screenTitleController(): ScreenTitleController
    fun exerciseLibraryFeatureApi(): ExerciseLibraryFeatureApi
}