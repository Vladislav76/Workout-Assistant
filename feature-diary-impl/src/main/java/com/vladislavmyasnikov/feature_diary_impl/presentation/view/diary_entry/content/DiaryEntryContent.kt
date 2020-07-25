package com.vladislavmyasnikov.feature_diary_impl.presentation.view.diary_entry.content

import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.VMFragment
import com.vladislavmyasnikov.common.utils.TimePointFormatter
import com.vladislavmyasnikov.feature_diary_impl.R
import com.vladislavmyasnikov.feature_diary_impl.domain.model.FullDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.presentation.viewmodel.DiaryEntryVM
import kotlinx.android.synthetic.main.content_diary_entry_details.*
import javax.inject.Inject

class DiaryEntryContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<FullDiaryEntry>(R.layout.content_diary_entry_details) {

    override val viewModel: DiaryEntryVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(DiaryEntryVM::class.java)
    }

    override fun onReceiveItem(item: FullDiaryEntry) {
        super.onReceiveItem(item)
        time_period.text = TimePointFormatter.formatInterval(item.startTime, item.endTime, TimePointFormatter.HOUR_MINUTE_FORMAT)
        time_duration.text = TimePointFormatter.format(item.duration, TimePointFormatter.HOUR_MINUTE_FORMAT)
    }

    override fun onReceivePacket(message: Message) {
        if (message is Message.KeyDataResponseMessage) {
            viewModel.fetch(message.id)
        }
    }
}