package com.vladislavmyasnikov.feature_exercise_library_impl.api_impl

import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.core_components.interfaces.FragmentController
import com.vladislavmyasnikov.feature_exercise_library_api.ExerciseLibraryLauncher
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.ExerciseListFragment
import javax.inject.Inject

@PerFeature
class ExerciseLibraryLauncherImpl @Inject constructor(private val fragmentController: FragmentController) : ExerciseLibraryLauncher {

    override fun launch() {
        val fragment = ExerciseListFragment.newInstance()
        fragmentController.addFragmentOnTop(fragment)
    }
}