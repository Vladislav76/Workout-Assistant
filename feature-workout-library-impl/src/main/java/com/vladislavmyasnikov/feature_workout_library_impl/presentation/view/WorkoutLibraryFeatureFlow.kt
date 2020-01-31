package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.FlowFragment
import com.vladislavmyasnikov.feature_workout_library_impl.di.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.Screens
import com.vladislavmyasnikov.features_api.workout_library.WorkoutLibraryLauncher
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutLibraryFeatureFlow @Inject constructor() : FlowFragment(), WorkoutLibraryLauncher {

    override val label = "WORKOUT_LIBRARY_FEATURE_FF"

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder

    @Inject
    override lateinit var router: Router

    @Inject
    override lateinit var bus: SharedBus

    override fun inject() {
        super.inject()
        WorkoutLibraryFeatureComponent.get().inject(this)
        fragmentManager?.fragmentFactory = WorkoutLibraryFeatureComponent.get().fragmentFactory
    }

    override fun onStartUp() {
        super.onStartUp()
        router.replaceScreen(Screens.WorkoutListScreen())
    }

    override fun launch(): Fragment {
        return this
    }
}