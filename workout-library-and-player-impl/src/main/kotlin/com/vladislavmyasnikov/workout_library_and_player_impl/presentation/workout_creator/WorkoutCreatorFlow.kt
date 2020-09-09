package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator

import com.vladislavmyasnikov.common.arch.component.FlowFragment
import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutCreatorFlowLauncher
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.WorkoutFeatureComponent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.NavigationComponentStore
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.Screens
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutCreatorFlow @Inject constructor(
        override val navigatorHolder: NavigatorHolder,
        override val router: Router
) : FlowFragment() {

    @PerFlow
    class Launcher @Inject constructor() : WorkoutCreatorFlowLauncher {

        override val flowClass = WorkoutCreatorFlow::class
        override val fragmentFactory = WorkoutFeatureComponent.get().fragmentFactory
    }

    override val fragmentFactory = WorkoutFeatureComponent.get().fragmentFactory

    override val initialScreen by lazy { NavigationComponentStore.getScreen(Screens.WorkoutSetListScreen) }
}