package com.vladislavmyasnikov.feature_workout_library_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.WorkoutListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class WorkoutListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            return WorkoutListFragment.newInstance()
        }
    }
}