package com.vladislavmyasnikov.feature_exercise_library_impl.di

import com.vladislavmyasnikov.core_components.interfaces.ContextHolder
import com.vladislavmyasnikov.core_components.interfaces.FragmentController
import com.vladislavmyasnikov.core_components.interfaces.ScreenTitleController

interface ExerciseLibraryFeatureDependencies {

    fun contextHolder(): ContextHolder
    fun screenTitleController(): ScreenTitleController
    fun fragmentController(): FragmentController
}