package com.vladislavmyasnikov.sample_app.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.sample_app.di.FeatureProxyInjector
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
}