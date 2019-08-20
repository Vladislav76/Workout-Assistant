package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view

import com.vladislavmyasnikov.core_components.view.ContainerFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.di.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.Screens

class FlowFragment : ContainerFragment() {

    override fun injecting() {
        super.injecting()
        ExerciseLibraryFeatureComponent.get().inject(this)
    }

    override fun initialLaunch() {
        super.initialLaunch()
        router.replaceScreen(Screens.ExerciseListScreen())
    }

    companion object {
        fun newInstance(): FlowFragment {
            return FlowFragment()
        }
    }
}