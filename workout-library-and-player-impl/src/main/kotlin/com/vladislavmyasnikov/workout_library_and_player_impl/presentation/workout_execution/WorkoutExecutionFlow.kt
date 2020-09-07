package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_execution

import android.os.Bundle
import com.vladislavmyasnikov.common.arch.component.FlowFragment
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutExecutionLauncher
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.WorkoutFeatureComponent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.NavigationComponentStore
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.Screens
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutExecutionFlow @Inject constructor() : FlowFragment(), WorkoutExecutionLauncher {

    private companion object {
        const val ARG_WORKOUT_ID = "workout_id"
    }

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
        router.newRootScreen(NavigationComponentStore.getScreen(Screens.WorkoutPlayerScreen(requireArguments().getLong(ARG_WORKOUT_ID))))
    }

    override fun launch(workoutId: Long): FlowFragment {
        arguments = Bundle().apply {
            putLong(ARG_WORKOUT_ID, workoutId)
        }
        return this
    }
}