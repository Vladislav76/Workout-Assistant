package com.vladislavmyasnikov.feature_workout_library_api

import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder

interface WorkoutLibraryFeatureApi {

    fun workoutLibraryLauncher(): WorkoutLibraryLauncher
    fun labelLibraryHolder(): LabelLibraryHolder
}