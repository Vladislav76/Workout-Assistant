package com.vladislavmyasnikov.features_api.exercise_library

import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder

interface ExerciseLibraryFeatureApi {

    fun exerciseLibraryLauncher(): ExerciseLibraryLauncher
    fun exerciseLibraryInteractor(): ExerciseLibraryInteractor
    fun labelLibraryHolder(): LabelLibraryHolder
}