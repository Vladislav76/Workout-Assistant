package com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.HostFragment
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.di.component.DiaryFeatureComponent
import com.vladislavmyasnikov.feature_diary_impl.presentation.Screens
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.content.DiaryEntryListContent
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.content.DiaryEntryListToolbarContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class DiaryEntryListScreenHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.two_fragment_container) {

    override val children = listOf(
            R.id.header_container to DiaryEntryListToolbarContent::class.java,
            R.id.body_container to DiaryEntryListContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = DiaryFeatureComponent.get().diaryEntryListComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        if (super.onBackPressed()) DiaryFeatureComponent.get().diaryEntryListComponent.resetValue()
        return true
    }

    override fun onReceivePacket(message: Message) {
        if (message is Message.ItemClickMessage) {
            router.navigateTo(Screens.DiaryEntryDetailsScreen())
            bus.sendPacket(Message.KeyDataResponseMessage(message.id))
        }
    }
}