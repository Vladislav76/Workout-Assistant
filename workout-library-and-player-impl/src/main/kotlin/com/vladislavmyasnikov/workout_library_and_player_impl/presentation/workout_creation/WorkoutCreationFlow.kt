package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.arch.component.FlowFragment
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutCreationLauncher
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.WorkoutFeatureComponent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.NavigationComponentStore
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.Screens
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutCreationFlow @Inject constructor() : FlowFragment(), WorkoutCreationLauncher {

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder

    @Inject
    override lateinit var router: Router

    override fun inject() {
        super.inject()
        WorkoutFeatureComponent.get().inject(this)
        fragmentManager?.fragmentFactory = WorkoutFeatureComponent.get().fragmentFactory
    }

    override fun onStartUp() {
        super.onStartUp()
        router.newRootScreen(NavigationComponentStore.getScreen(Screens.WorkoutSetListScreen))
    }

    override fun launch(): Fragment {
        return this
    }
}