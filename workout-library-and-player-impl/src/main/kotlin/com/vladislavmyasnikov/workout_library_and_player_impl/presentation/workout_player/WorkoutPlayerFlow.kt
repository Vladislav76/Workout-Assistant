package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player

import android.os.Bundle
import com.vladislavmyasnikov.common.arch.component.FlowFragment
import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutPlayerFlowLauncher
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.WorkoutFeatureComponent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.NavigationComponentStore
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.Screens
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutPlayerFlow @Inject constructor(
        override val navigatorHolder: NavigatorHolder,
        override val router: Router
) : FlowFragment() {

    @PerFlow
    class Launcher @Inject constructor() : WorkoutPlayerFlowLauncher {

        override val flowClass = WorkoutPlayerFlow::class
        override val fragmentFactory = WorkoutFeatureComponent.get().fragmentFactory
    }

    private companion object {
        const val ARG_WORKOUT_ID = "workout_id"
    }

    override val fragmentFactory = WorkoutFeatureComponent.get().fragmentFactory

    override val initialScreen by lazy { NavigationComponentStore.getScreen(Screens.WorkoutPlayerScreen(requireArguments().getLong(ARG_WORKOUT_ID))) }

    fun putArguments(workoutId: Long) {
        arguments = Bundle().apply {
            putLong(ARG_WORKOUT_ID, workoutId)
        }
    }
}