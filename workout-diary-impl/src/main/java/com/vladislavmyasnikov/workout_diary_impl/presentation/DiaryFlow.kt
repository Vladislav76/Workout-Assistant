package com.vladislavmyasnikov.workout_diary_impl.presentation

import com.vladislavmyasnikov.common.arch.component.FlowFragment
import com.vladislavmyasnikov.common.arch.component.FlowLauncher
import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.workout_diary_impl.di.component.DiaryFeatureComponent
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class DiaryFlow @Inject constructor(
        override val navigatorHolder: NavigatorHolder,
        override val router: Router
) : FlowFragment() {

    @PerFlow
    class Launcher @Inject constructor() : FlowLauncher {

        override val flowClass = DiaryFlow::class
        override val fragmentFactory = DiaryFeatureComponent.get().fragmentFactory
    }

    override val fragmentFactory = DiaryFeatureComponent.get().fragmentFactory

    override val initialScreen by lazy { Screens.DiaryEntryListScreen() }
}