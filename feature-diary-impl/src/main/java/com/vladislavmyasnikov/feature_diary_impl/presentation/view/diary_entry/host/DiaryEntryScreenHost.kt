package com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.HostFragment
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.di.component.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry.content.DiaryEntryContent
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry.content.DiaryEntryToolbarContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class DiaryEntryScreenHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.two_fragment_container) {

    override val children = listOf(
            R.id.header_container to DiaryEntryToolbarContent::class.java,
            R.id.body_container to DiaryEntryContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = DiaryFeatureComponent.get().diaryEntryComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        if (super.onBackPressed()) DiaryFeatureComponent.get().diaryEntryComponent.resetValue()
        return true
    }

    override fun onReceivePacket(message: Message) {
        if (message is Message.KeyDataResponseMessage) {
            bus.sendNoise()
        }
    }
}