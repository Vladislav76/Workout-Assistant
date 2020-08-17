package com.vladislavmyasnikov.workout_library_and_player_impl.di

import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.exercise_library_api.ExerciseLibraryFeatureApi

interface FeatureDependencies {

    fun contextHolder(): ContextHolder
    fun exerciseLibraryFeatureApi(): ExerciseLibraryFeatureApi
}