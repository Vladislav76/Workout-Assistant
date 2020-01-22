package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.FlowFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.di.FeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.Screens
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryLauncher
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FeatureFlowFragment @Inject constructor() : FlowFragment(), ExerciseLibraryLauncher {

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder

    @Inject
    override lateinit var router: Router

    @Inject
    override lateinit var bus: SharedBus

    override val label = "exercise_feature_flow_fragment"

    override fun inject() {
        super.inject()
        FeatureComponent.get().inject(this)
        fragmentManager?.fragmentFactory = FeatureComponent.get().fragmentFactory
    }

    override fun onStartUp() {
        super.onStartUp()
        router.replaceScreen(Screens.ExerciseListScreen())
    }

    override fun launch(): Fragment {
        return this
    }
}