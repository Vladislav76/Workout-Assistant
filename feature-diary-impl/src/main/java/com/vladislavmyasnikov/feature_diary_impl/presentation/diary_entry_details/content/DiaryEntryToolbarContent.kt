package com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_details.content

import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.presentation.view.components.VMToolbarFragment
import com.vladislavmyasnikov.common.utils.DateFormatType
import com.vladislavmyasnikov.feature_diary_api.domain.entity.DiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_details.viewmodel.DiaryEntryVM
import javax.inject.Inject

class DiaryEntryToolbarContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMToolbarFragment<DiaryEntry>() {

    override val viewModel by lazy { injectViewModel<DiaryEntryVM>(viewModelFactory) }

    override fun onReceiveItem(item: DiaryEntry) {
        super.onReceiveItem(item)
        setTitle(DateFormatType.DAY_MONTH_YEAR.format(item.date))
    }
}