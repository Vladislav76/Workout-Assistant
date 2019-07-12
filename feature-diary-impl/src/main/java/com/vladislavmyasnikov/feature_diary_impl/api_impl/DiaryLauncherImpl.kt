package com.vladislavmyasnikov.feature_diary_impl.api_impl

import com.vladislavmyasnikov.core_components.interfaces.FragmentController
import com.vladislavmyasnikov.core_components.di.PerFeature
import com.vladislavmyasnikov.diary_feature_api.DiaryLauncher
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.DiaryEntryListFragment
import javax.inject.Inject

@PerFeature
class DiaryLauncherImpl @Inject constructor(private val fragmentController: FragmentController) : DiaryLauncher {

    override fun launch() {
        val fragment = DiaryEntryListFragment.newInstance()
        fragmentController.addFragmentOnTop(fragment)
    }
}