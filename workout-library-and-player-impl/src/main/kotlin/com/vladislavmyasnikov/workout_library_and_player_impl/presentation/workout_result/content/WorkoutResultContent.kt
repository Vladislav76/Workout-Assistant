package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_result.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.communication.RequestMessageType
import com.vladislavmyasnikov.common.arch.component.VMFragment
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.utils.TimePointFormatType
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_execution.WorkoutResult
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_result.viewmodel.WorkoutResultVM
import kotlinx.android.synthetic.main.content_workout_result.*
import javax.inject.Inject

class WorkoutResultContent @Inject constructor(
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<WorkoutResult>(R.layout.content_workout_result) {

    override val viewModel by lazy { injectViewModel<WorkoutResultVM>(viewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ok_button.setOnClickListener { sendMessage(Message.RequestMessage(RequestMessageType.TRANSITION_REQUEST)) }
        viewModel.request()
    }

    override fun onReceiveItem(item: WorkoutResult) {
        workout_duration_field.text = TimePointFormatType.DURATION.format(item.duration)
        productivity_bar.progress = item.workoutProductivity
        workout_productivity_field.text = "${item.workoutProductivity}%"
    }
}