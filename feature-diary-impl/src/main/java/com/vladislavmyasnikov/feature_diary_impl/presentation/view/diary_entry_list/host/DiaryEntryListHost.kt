package com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry_list.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.HostFragment
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.di.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.presentation.Screens
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry_list.content.DiaryEntryListContent
import com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry_list.content.DiaryEntryListToolbarContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class DiaryEntryListHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.two_fragment_container) {

    override val label = "DIARY_ENTRY_LIST_HF"

    override val children = listOf(
            R.id.top_container to DiaryEntryListToolbarContent::class.java,
            R.id.bottom_container to DiaryEntryListContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = DiaryFeatureComponent.get().diaryEntryListComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemClickMessage) {
            router.navigateTo(Screens.DiaryEntryDetailsScreen())
        } else super.onReceivePacket(packet)
    }

    override fun onBackPressed(): Boolean {
        if (super.onBackPressed()) DiaryFeatureComponent.get().diaryEntryListComponent.resetValue()
        return true
    }
}