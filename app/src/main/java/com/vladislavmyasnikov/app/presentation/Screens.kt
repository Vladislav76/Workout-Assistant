package com.vladislavmyasnikov.app.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.app.di.FeatureProxyInjector
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class ExercisesScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            return FeatureProxyInjector.getExerciseLibraryFeature().exerciseLibraryLauncher().launch()
        }
    }

    class DiaryScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            return FeatureProxyInjector.getDiaryFeature().diaryLauncher().launch()
        }
    }

    class WorkoutsScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            return FeatureProxyInjector.getWorkoutLibraryFeature().workoutLibraryLauncher().launch()
        }
    }
}