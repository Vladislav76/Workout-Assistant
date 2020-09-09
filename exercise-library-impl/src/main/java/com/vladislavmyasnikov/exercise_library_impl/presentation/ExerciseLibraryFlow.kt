package com.vladislavmyasnikov.exercise_library_impl.presentation

import com.vladislavmyasnikov.common.arch.component.FlowFragment
import com.vladislavmyasnikov.common.arch.component.FlowLauncher
import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.exercise_library_impl.di.component.ExerciseLibraryFeatureComponent
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ExerciseLibraryFlow @Inject constructor(
        override val navigatorHolder: NavigatorHolder,
        override val router: Router
) : FlowFragment() {

    @PerFlow
    class Launcher @Inject constructor() : FlowLauncher {

        override val flowClass = ExerciseLibraryFlow::class
        override val fragmentFactory = ExerciseLibraryFeatureComponent.get().fragmentFactory
    }

    override val fragmentFactory = ExerciseLibraryFeatureComponent.get().fragmentFactory

    override val initialScreen by lazy { Screens.ExerciseListScreen() }
}