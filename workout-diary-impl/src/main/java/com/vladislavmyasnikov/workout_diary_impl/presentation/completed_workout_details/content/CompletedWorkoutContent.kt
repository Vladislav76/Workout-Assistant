package com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.RequestMessageType
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.VMFragment
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.interfaces.MessageSender
import com.vladislavmyasnikov.common.utils.DateFormatType
import com.vladislavmyasnikov.common.utils.TimePointFormatType
import com.vladislavmyasnikov.workout_diary_impl.R
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.viewmodel.CompletedWorkoutVM
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.CompletedWorkout
import kotlinx.android.synthetic.main.content_diary_entry_details.*
import javax.inject.Inject

class CompletedWorkoutContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<CompletedWorkout>(R.layout.content_diary_entry_details) {

    override val viewModel by lazy { injectViewModel<CompletedWorkoutVM>(viewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendMessage(Message.RequestMessage(RequestMessageType.KEY_DATA_REQUEST))
    }

    override fun onReceiveItem(item: CompletedWorkout) {
        workout_duration.text = TimePointFormatType.DURATION.format(item.duration)
        workout_date.text = DateFormatType.DAY_MONTH_YEAR.format(item.date)
        workout_name.text = item.workoutName
        workout_productivity.text = "${item.workoutProductivity} %"
        diary_entry_description.text = item.description
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.KeyDataResponseMessage) {
            viewModel.request(message.id)
        }
    }
}