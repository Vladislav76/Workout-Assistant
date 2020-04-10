package com.vladislavmyasnikov.feature_diary_impl.presentation.view

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.feature_diary_impl.di.component.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry.host.DiaryEntryScreenHost
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry_list.host.DiaryEntryListScreenHost
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class DiaryEntryListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = DiaryEntryListScreenHost::class.java
            val factory = DiaryFeatureComponent.get().fragmentFactory
            return factory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }

    class DiaryEntryDetailsScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = DiaryEntryScreenHost::class.java
            val factory = DiaryFeatureComponent.get().fragmentFactory
            return factory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }
}