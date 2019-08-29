package com.vladislavmyasnikov.feature_workout_library_impl.di

import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.common.interfaces.ScreenTitleController
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryFeatureApi

interface WorkoutLibraryFeatureDependencies {

    fun contextHolder(): ContextHolder
    fun screenTitleController(): ScreenTitleController
    fun exerciseLibraryFeatureApi(): ExerciseLibraryFeatureApi
}