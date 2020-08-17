package com.vladislavmyasnikov.workout_diary_impl.di

import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutLibraryFeatureApi

interface FeatureDependencies {

    fun contextHolder(): ContextHolder
    fun workoutLibraryFeatureApi(): WorkoutLibraryFeatureApi
}