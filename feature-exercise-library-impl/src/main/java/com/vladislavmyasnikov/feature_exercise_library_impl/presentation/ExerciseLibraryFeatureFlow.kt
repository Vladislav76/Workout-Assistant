package com.vladislavmyasnikov.feature_exercise_library_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.FlowFragment
import com.vladislavmyasnikov.feature_exercise_library_api.ExerciseLibraryLauncher
import com.vladislavmyasnikov.feature_exercise_library_impl.di.component.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.Screens
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ExerciseLibraryFeatureFlow @Inject constructor() : FlowFragment(), ExerciseLibraryLauncher {

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