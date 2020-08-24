package com.vladislavmyasnikov.workout_diary_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.arch.component.FlowFragment
import com.vladislavmyasnikov.workout_diary_api.DiaryLauncher
import com.vladislavmyasnikov.workout_diary_impl.di.component.DiaryFeatureComponent
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class DiaryFlow @Inject constructor() : FlowFragment(), DiaryLauncher {

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder

    @Inject
    override lateinit var router: Router

    override fun inject() {
        super.inject()
        DiaryFeatureComponent.get().inject(this)
        fragmentManager?.fragmentFactory = DiaryFeatureComponent.get().fragmentFactory
    }

    override fun onStartUp() {
        super.onStartUp()
        router.newRootChain(Screens.DiaryEntryListScreen())
    }

    override fun launch(): Fragment {
        return this
    }
}