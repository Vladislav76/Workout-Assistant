package com.vladislavmyasnikov.feature_diary_impl.presentation.view

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.arch_components.fundamental.FlowFragment
import com.vladislavmyasnikov.feature_diary_impl.di.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.presentation.Screens
import com.vladislavmyasnikov.features_api.diary.DiaryLauncher
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class DiaryFeatureFlow @Inject constructor() : FlowFragment(), DiaryLauncher {

    override val label = "DIARY_FEATURE_FF"

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