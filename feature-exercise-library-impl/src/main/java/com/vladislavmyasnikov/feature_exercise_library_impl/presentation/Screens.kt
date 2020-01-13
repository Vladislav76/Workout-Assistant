package com.vladislavmyasnikov.feature_exercise_library_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.view.host.ExerciseLibraryFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class ExerciseListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            return ExerciseLibraryFragment.newInstance()
        }
    }

//    class ExerciseDetailsScreen(private val exerciseId: Long, private val title: String) : SupportAppScreen() {
//
//        override fun getFragment(): Fragment {
//            return ExerciseFragment.newInstance(exerciseId, title)
//        }
//    }
}