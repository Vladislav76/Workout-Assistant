package com.vladislavmyasnikov.workout_library_and_player_api

import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder

interface WorkoutLibraryFeatureApi {

    fun workoutLibraryLauncher(): WorkoutLibraryLauncher
    fun workoutCreationLauncher(): WorkoutCreationLauncher
    fun labelLibraryHolder(): LabelLibraryHolder
    fun workoutLibraryInteractor(): WorkoutLibraryInteractor
}