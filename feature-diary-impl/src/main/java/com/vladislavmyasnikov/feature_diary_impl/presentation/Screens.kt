package com.vladislavmyasnikov.feature_diary_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.feature_diary_impl.di.component.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_details.host.DiaryEntryScreenHost
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.host.DiaryEntryListScreenHost
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    private companion object {
        val sharedFragmentFactory = DiaryFeatureComponent.get().fragmentFactory
    }

    class DiaryEntryListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = DiaryEntryListScreenHost::class.java
            return sharedFragmentFactory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }

    class DiaryEntryDetailsScreen(private val diaryEntryId: Long) : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = DiaryEntryScreenHost::class.java
            return sharedFragmentFactory.instantiate(clazz.classLoader!!, clazz.name)
                    .also { fragment -> (fragment as DiaryEntryScreenHost).putArguments(diaryEntryId) }
        }
    }
}