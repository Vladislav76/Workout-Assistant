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
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutProcessState
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel.WorkoutPlayerVM
import kotlinx.android.synthetic.main.content_control_panel.*
import javax.inject.Inject

class ControlPanelContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<WorkoutProcessState>(R.layout.content_control_panel) {

    override val viewModel by lazy { injectViewModel<WorkoutPlayerVM>(viewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        next_button.setOnClickListener { viewModel.next() }
        stop_button.setOnClickListener { viewModel.stop() }
        pause_button.setOnClickListener { viewModel.pause() }
        resume_button.setOnClickListener { viewModel.resume() }

        if (savedInstanceState == null) {
            sendMessage(Message.RequestMessage(RequestMessageType.KEY_DATA_REQUEST))
        }
    }

    override fun onReceiveItem(item: WorkoutProcessState) {
        when (item) {
            WorkoutProcessState.STARTED -> {
                resume_button.visibility = View.GONE
                pause_button.visibility = View.VISIBLE
            }
            WorkoutProcessState.PAUSED -> {
                resume_button.visibility = View.VISIBLE
                pause_button.visibility = View.GONE
            }
            WorkoutProcessState.FINISHED -> {
                // TODO: show warning dialog
                sendMessage(Message.RequestMessage(RequestMessageType.TRANSITION_REQUEST))
//                resume_button.visibility = View.GONE
//                pause_button.visibility = View.GONE
//                stop_button.visibility = View.GONE
//                next_button.visibility = View.GONE
            }
        }
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.KeyDataResponseMessage) {
            viewModel.request(message.id)
        }
    }
}