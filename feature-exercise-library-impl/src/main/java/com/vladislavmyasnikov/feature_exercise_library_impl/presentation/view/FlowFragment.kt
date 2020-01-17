package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.view.ContainerFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.di.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.Screens
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryLauncher
import javax.inject.Inject

class FlowFragment @Inject constructor() : ContainerFragment(), ExerciseLibraryLauncher {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun injecting() {
        super.injecting()
        ExerciseLibraryFeatureComponent.get().inject(this)
        fragmentManager?.fragmentFactory = fragmentFactory
    }

    override fun initialLaunch() {
        super.initialLaunch()
        router.replaceScreen(Screens.ExerciseListScreen())
    }

    override fun launch(): Fragment {
        return this
    }
}