package com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_result.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.RequestMessageType
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.VMFragment
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.utils.TimePointFormatType
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.CompletedWorkoutResult
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_result.viewmodel.CompletedWorkoutResultVM
import kotlinx.android.synthetic.main.content_workout_result.*
import javax.inject.Inject

class CompletedWorkoutResultContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<CompletedWorkoutResult>(R.layout.content_workout_result) {

    override val viewModel by lazy { injectViewModel<CompletedWorkoutResultVM>(viewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ok_button.setOnClickListener {
            sendMessage(Message.RequestMessage(RequestMessageType.TRANSITION_REQUEST))
        }
        viewModel.request()
    }

    override fun onReceiveItem(item: CompletedWorkoutResult) {
        workout_duration_field.text = TimePointFormatType.DURATION.format(item.duration)
        productivity_bar.progress = item.workoutProductivity
        workout_productivity_field.text = "${item.workoutProductivity}%"
    }
}