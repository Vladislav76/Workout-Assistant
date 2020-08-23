package com.vladislavmyasnikov.exercise_library_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.FlowFragment
import com.vladislavmyasnikov.exercise_library_api.ExerciseLibraryLauncher
import com.vladislavmyasnikov.exercise_library_impl.di.component.ExerciseLibraryFeatureComponent
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ExerciseLibraryFlow @Inject constructor() : FlowFragment(), ExerciseLibraryLauncher {

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
        router.newRootChain(Screens.ExerciseListScreen())
    }

    override fun launch(): Fragment {
        return this
    }
}