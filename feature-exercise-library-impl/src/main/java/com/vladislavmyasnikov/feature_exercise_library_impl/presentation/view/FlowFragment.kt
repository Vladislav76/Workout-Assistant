package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view

import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.view.ContainerFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.di.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.Screens
import javax.inject.Inject

class FlowFragment : ContainerFragment() {

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

    companion object {
        fun newInstance(): FlowFragment {
            return FlowFragment()
        }
    }
}