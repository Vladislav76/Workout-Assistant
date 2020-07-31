package com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry_list.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.Event
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.presentation.view.components.VMListFragment
import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.presentation.adapters.DiaryEntryAdapter
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodel.DiaryEntryListVM
import javax.inject.Inject

class DiaryEntryListContent @Inject constructor(
        override val bus: SharedBus,
        override val adapter: DiaryEntryAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
): VMListFragment<ShortDiaryEntry>() {

    override val viewModel: DiaryEntryListVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(DiaryEntryListVM::class.java)
    }

    override val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            bus.sendPacket(Message.ItemClickMessage(id))
        }
    }

    override val itemClickCallbackInSelectMode = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            viewModel.select(id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch()
    }

    override fun onReceiveEvent(event: Event) {
        super.onReceiveEvent(event)
        when (event) {
            is Event.SelectionModeChangeEvent -> {
                debugMessage(event.toString())
                adapter.isSelectMode = event.isSelected
            }
        }
    }
}