package com.vladislavmyasnikov.feature_workout_library_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.FlowFragment
import com.vladislavmyasnikov.feature_workout_library_api.WorkoutDiaryLauncher
import com.vladislavmyasnikov.feature_workout_library_impl.di.component.WorkoutLibraryFeatureComponent
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutDiaryFlow @Inject constructor() : FlowFragment(), WorkoutDiaryLauncher {

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
        router.replaceScreen(Screens.CompletedWorkoutListScreen())
    }

    override fun launch(): Fragment {
        return this
    }
}