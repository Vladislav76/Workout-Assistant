package com.vladislavmyasnikov.features_api.workout_library

import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder

interface WorkoutLibraryFeatureApi {

    fun workoutLibraryLauncher(): WorkoutLibraryLauncher
    fun labelLibraryHolder(): LabelLibraryHolder
}