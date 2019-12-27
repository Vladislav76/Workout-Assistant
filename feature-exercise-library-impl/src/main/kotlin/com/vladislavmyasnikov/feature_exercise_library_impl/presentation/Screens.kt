package com.vladislavmyasnikov.feature_exercise_library_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.ExerciseFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.ExerciseListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class ExerciseListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            return ExerciseListFragment.newInstance()
        }
    }

    class ExerciseDetailsScreen(private val exerciseId: Long, private val title: String) : SupportAppScreen() {

        override fun getFragment(): Fragment {
            return ExerciseFragment.newInstance(exerciseId, title)
        }
    }
}