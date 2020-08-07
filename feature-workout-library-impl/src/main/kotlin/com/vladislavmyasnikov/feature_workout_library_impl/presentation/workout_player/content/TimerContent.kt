package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.RequestMessageType
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.interfaces.MessageSender
import com.vladislavmyasnikov.common.arch.fundamental.VMFragment
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.TimerValue
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel.WorkoutTimerVM
import kotlinx.android.synthetic.main.content_timer.*
import javax.inject.Inject

class TimerContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<TimerValue>(R.layout.content_timer) {

    override val viewModel by lazy { injectViewModel<WorkoutTimerVM>(viewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            sendMessage(Message.RequestMessage(RequestMessageType.CONTENT_REQUEST), this)
        }
    }

    override fun onReceiveItem(item: TimerValue) {
        // TODO: add time formatter
        timer.text = String.format("%02d:%02d:%02d", item.hours, item.minutes, item.seconds)
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.RequestMessage && message.type == RequestMessageType.CONTENT_REQUEST) {
            viewModel.request()
        }
    }
}