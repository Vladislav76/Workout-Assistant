package com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry.content

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.components.VMToolbarFragment
import com.vladislavmyasnikov.common.utils.DateFormatter
import com.vladislavmyasnikov.feature_diary_impl.domain.FullDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodels.DiaryEntryVM
import javax.inject.Inject

class DiaryEntryToolbarContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMToolbarFragment<FullDiaryEntry>() {

    override val label = "DIARY_ENTRY_TOOLBAR_CF"

    override lateinit var viewModel: DiaryEntryVM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DiaryEntryVM::class.java)
    }

    override fun onReceiveItem(item: FullDiaryEntry) {
        super.onReceiveItem(item)
        setTitle(DateFormatter.format(item.date, DateFormatter.DAY_MONTH_YEAR_FORMAT))
    }
}