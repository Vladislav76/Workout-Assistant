package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view

import com.vladislavmyasnikov.common.legacy.view.ContainerFragment
import com.vladislavmyasnikov.feature_workout_library_impl.di.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.Screens

class FlowFragment : ContainerFragment() {

    override fun injecting() {
        super.injecting()
        WorkoutLibraryFeatureComponent.get().inject(this)
    }

    override fun initialLaunch() {
        super.initialLaunch()
        router.replaceScreen(Screens.WorkoutListScreen())
    }

    companion object {
        fun newInstance(): FlowFragment {
            return FlowFragment()
        }
    }
}