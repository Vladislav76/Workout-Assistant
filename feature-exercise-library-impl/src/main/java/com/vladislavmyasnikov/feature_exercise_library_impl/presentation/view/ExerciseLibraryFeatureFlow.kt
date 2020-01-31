package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.FlowFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.di.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.Screens
import com.vladislavmyasnikov.features_api.exercise_library.ExerciseLibraryLauncher
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ExerciseLibraryFeatureFlow @Inject constructor() : FlowFragment(), ExerciseLibraryLauncher {

    override val label = "EXERCISE_LIBRARY_FEATURE_FF"

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder

    @Inject
    override lateinit var router: Router

    @Inject
    override lateinit var bus: SharedBus

    override fun inject() {
        super.inject()
        ExerciseLibraryFeatureComponent.get().inject(this)
        fragmentManager?.fragmentFactory = ExerciseLibraryFeatureComponent.get().fragmentFactory
    }

    override fun onStartUp() {
        super.onStartUp()
        router.replaceScreen(Screens.ExerciseListScreen())
    }

    override fun launch(): Fragment {
        return this
    }
}