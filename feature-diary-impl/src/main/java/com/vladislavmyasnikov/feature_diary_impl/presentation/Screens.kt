package com.vladislavmyasnikov.feature_diary_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.DiaryEntryFragment
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.DiaryEntryListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class DiaryEntryListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            return DiaryEntryListFragment.newInstance()
        }
    }

    class DiaryEntryDetailsScreen(private val entryId: Long, private val title: String) : SupportAppScreen() {

        override fun getFragment(): Fragment {
            return DiaryEntryFragment.newInstance(entryId, title)
        }
    }
}