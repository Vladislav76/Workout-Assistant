package com.vladislavmyasnikov.feature_diary_impl.di

import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.feature_workout_library_api.WorkoutLibraryFeatureApi

interface FeatureDependencies {

    fun contextHolder(): ContextHolder
    fun workoutLibraryFeatureApi(): WorkoutLibraryFeatureApi
}