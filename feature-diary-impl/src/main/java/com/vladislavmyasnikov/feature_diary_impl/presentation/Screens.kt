package com.vladislavmyasnikov.feature_diary_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.feature_diary_impl.di.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry.host.DiaryEntryHost
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry_list.host.DiaryEntryListHost
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class DiaryEntryListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = DiaryEntryListHost::class.java
            val factory = DiaryFeatureComponent.get().fragmentFactory
            return factory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }

    class DiaryEntryDetailsScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = DiaryEntryHost::class.java
            val factory = DiaryFeatureComponent.get().fragmentFactory
            return factory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }
}