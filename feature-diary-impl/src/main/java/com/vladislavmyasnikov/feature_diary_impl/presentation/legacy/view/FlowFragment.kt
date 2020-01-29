package com.vladislavmyasnikov.feature_diary_impl.presentation.legacy.view

import com.vladislavmyasnikov.common.legacy.view.ContainerFragment

class FlowFragment : ContainerFragment() {

    override fun injecting() {
        super.injecting()
//        FeatureComponent.get().inject(this)
    }

    override fun initialLaunch() {
        super.initialLaunch()
//        router.replaceScreen(Screens.DiaryEntryListScreen())
    }

    companion object {
        fun newInstance(): FlowFragment {
            return FlowFragment()
        }
    }
}