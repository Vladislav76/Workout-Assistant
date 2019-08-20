package com.vladislavmyasnikov.feature_diary_impl.presentation.view

import com.vladislavmyasnikov.core_components.view.ContainerFragment
import com.vladislavmyasnikov.feature_diary_impl.di.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.presentation.Screens

class FlowFragment : ContainerFragment() {

    override fun injecting() {
        super.injecting()
        DiaryFeatureComponent.get().inject(this)
    }

    override fun initialLaunch() {
        super.initialLaunch()
        router.replaceScreen(Screens.DiaryEntryListScreen())
    }

    companion object {
        fun newInstance(): FlowFragment {
            return FlowFragment()
        }
    }
}