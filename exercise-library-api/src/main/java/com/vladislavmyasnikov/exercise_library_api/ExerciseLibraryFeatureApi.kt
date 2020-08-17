package com.vladislavmyasnikov.exercise_library_api

import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder

interface ExerciseLibraryFeatureApi {

    fun exerciseLibraryLauncher(): ExerciseLibraryLauncher
    fun exerciseLibraryInteractor(): ExerciseLibraryInteractor
    fun labelLibraryHolder(): LabelLibraryHolder
}