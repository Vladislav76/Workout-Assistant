package com.vladislavmyasnikov.feature_exercise_book_impl.api_impl

import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.core_components.interfaces.FragmentController
import com.vladislavmyasnikov.feature_exercise_book_api.ExerciseBookLauncher
import com.vladislavmyasnikov.feature_exercise_book_impl.presentation.view.ExerciseListFragment
import javax.inject.Inject

@PerFeature
class ExerciseBookLauncherImpl @Inject constructor(private val fragmentController: FragmentController) : ExerciseBookLauncher {

    override fun launch() {
        val fragment = ExerciseListFragment.newInstance()
        fragmentController.addFragmentOnTop(fragment)
    }
}