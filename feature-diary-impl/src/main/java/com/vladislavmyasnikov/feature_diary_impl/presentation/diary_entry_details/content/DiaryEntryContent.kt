package com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_details.content

import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.VMFragment
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.utils.TimePointFormatType
import com.vladislavmyasnikov.feature_diary_api.domain.entity.DiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.presentation.diary_entry_details.viewmodel.DiaryEntryVM
import kotlinx.android.synthetic.main.content_diary_entry_details.*
import javax.inject.Inject

class DiaryEntryContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<DiaryEntry>(R.layout.content_diary_entry_details) {

    override val viewModel by lazy { injectViewModel<DiaryEntryVM>(viewModelFactory) }

    override fun onReceiveItem(item: DiaryEntry) {
        super.onReceiveItem(item)
        time_period.text = TimePointFormatType.HOUR_MINUTE.formatInterval(item.startTime, item.endTime)
        time_duration.text = TimePointFormatType.HOUR_MINUTE_SECOND.format(item.duration)
    }

    override fun onReceivePacket(message: Message) {
        if (message is Message.KeyDataResponseMessage) {
            viewModel.fetch(message.id)
        }
    }
}