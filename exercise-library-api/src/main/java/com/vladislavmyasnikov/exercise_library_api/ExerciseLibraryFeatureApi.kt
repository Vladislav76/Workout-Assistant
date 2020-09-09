package com.vladislavmyasnikov.exercise_library_api

import com.vladislavmyasnikov.common.arch.component.FlowLauncher
import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder

interface ExerciseLibraryFeatureApi {

    fun exerciseLibraryLauncher(): FlowLauncher
    fun exerciseLibraryInteractor(): ExerciseLibraryInteractor
    fun labelLibraryHolder(): LabelLibraryHolder
}