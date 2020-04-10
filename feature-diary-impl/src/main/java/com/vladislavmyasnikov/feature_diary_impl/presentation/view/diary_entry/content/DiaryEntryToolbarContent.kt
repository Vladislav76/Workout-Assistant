package com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry.content

import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.components.VMToolbarFragment
import com.vladislavmyasnikov.common.utils.DateFormatter
import com.vladislavmyasnikov.feature_diary_impl.domain.model.FullDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodel.DiaryEntryVM
import javax.inject.Inject

class DiaryEntryToolbarContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMToolbarFragment<FullDiaryEntry>() {

    override val viewModel: DiaryEntryVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(DiaryEntryVM::class.java)
    }

    override fun onReceiveItem(item: FullDiaryEntry) {
        super.onReceiveItem(item)
        setTitle(DateFormatter.format(item.date, DateFormatter.DAY_MONTH_YEAR_FORMAT))
    }
}