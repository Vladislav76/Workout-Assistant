package com.vladislavmyasnikov.app.presentation

import com.vladislavmyasnikov.app.di.FeatureProxyInjector

object Flows {

    val WORKOUT_LIBRARY_LAUNCHER = FeatureProxyInjector.getWorkoutLibraryFeature().workoutLibraryLauncher()
    val WORKOUT_DIARY_LAUNCHER = FeatureProxyInjector.getDiaryFeature().diaryLauncher()
    val EXERCISE_LIBRARY_LAUNCHER = FeatureProxyInjector.getExerciseLibraryFeature().exerciseLibraryLauncher()

    val WORKOUT_LIBRARY_FLOW_SCREEN = WORKOUT_LIBRARY_LAUNCHER.createScreen()
    val WORKOUT_DIARY_FLOW_SCREEN = WORKOUT_DIARY_LAUNCHER.createScreen()
    val EXERCISE_LIBRARY_FLOW_SCREEN = EXERCISE_LIBRARY_LAUNCHER.createScreen()
}