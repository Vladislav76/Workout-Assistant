package com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.arch_components.fundamental.HostFragment
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.di.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry.content.DiaryEntryContent
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry.content.DiaryEntryToolbarContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class DiaryEntryHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.two_fragment_container) {

    override val label = "DIARY_ENTRY_HF"

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

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemFetchRequest) {
            bus.sendNoise()
        }
    }
}