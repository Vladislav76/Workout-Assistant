package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_result.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.RequestMessageType
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.VMFragment
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.interfaces.MessageSender
import com.vladislavmyasnikov.common.utils.TimePointFormatType
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutResult
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_result.viewmodel.WorkoutResultVM
import kotlinx.android.synthetic.main.content_workout_result.*
import javax.inject.Inject

class WorkoutResultContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<WorkoutResult>(R.layout.content_workout_result) {

    override val viewModel by lazy { injectViewModel<WorkoutResultVM>(viewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ok_button.setOnClickListener {
            sendMessage(Message.RequestMessage(RequestMessageType.TRANSITION_REQUEST))
        }

        if (savedInstanceState == null) {
            sendMessage(Message.RequestMessage(RequestMessageType.CONTENT_REQUEST), this)
        }
    }

    override fun onReceiveItem(item: WorkoutResult) {
        workout_duration_field.text = TimePointFormatType.DURATION.format(item.duration)
        productivity_bar.progress = item.productivity
        workout_productivity_field.text = "${item.productivity}%"
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.RequestMessage && message.type == RequestMessageType.CONTENT_REQUEST) {
            viewModel.request()
        }
    }
}