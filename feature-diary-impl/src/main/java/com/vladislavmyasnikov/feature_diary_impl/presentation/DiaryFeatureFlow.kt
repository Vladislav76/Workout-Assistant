package com.vladislavmyasnikov.feature_diary_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.FlowFragment
import com.vladislavmyasnikov.feature_diary_api.DiaryLauncher
import com.vladislavmyasnikov.feature_diary_impl.di.component.DiaryFeatureComponent
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class DiaryFeatureFlow @Inject constructor() : FlowFragment(), DiaryLauncher {

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder

    @Inject
    override lateinit var router: Router

    @Inject
    override lateinit var bus: SharedBus

    override fun inject() {
        super.inject()
        DiaryFeatureComponent.get().inject(this)
        fragmentManager?.fragmentFactory = DiaryFeatureComponent.get().fragmentFactory
    }

    override fun onStartUp() {
        super.onStartUp()
        router.replaceScreen(Screens.DiaryEntryListScreen())
    }

    override fun launch(): Fragment {
        return this
    }
}