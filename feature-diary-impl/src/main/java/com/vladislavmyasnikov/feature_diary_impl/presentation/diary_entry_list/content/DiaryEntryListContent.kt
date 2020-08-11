package com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.presentation.view.components.VMListFragment
import com.vladislavmyasnikov.feature_diary_impl.domain.entity.ShortDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.adapter.DiaryEntryAdapter
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_list.viewmodel.DiaryEntryListVM
import javax.inject.Inject

class DiaryEntryListContent @Inject constructor(
        override val bus: SharedBus,
        override val adapter: DiaryEntryAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
): VMListFragment<ShortDiaryEntry>() {

    override val viewModel by lazy { injectViewModel<DiaryEntryListVM>(viewModelFactory) }

    override val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            sendMessage(Message.ItemClickMessage(id))
        }
    }

    override val itemClickCallbackInSelectMode = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.request()
    }
}