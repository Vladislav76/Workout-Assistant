package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.RequestMessageType
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.interfaces.MessageSender
import com.vladislavmyasnikov.common.arch.fundamental.VMFragment
import com.vladislavmyasnikov.common.extensions.exported_data_button
import com.vladislavmyasnikov.common.extensions.exported_decrease_button
import com.vladislavmyasnikov.common.extensions.exported_increase_button
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExerciseIndicators
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel.WorkoutExerciseMetricsVM
import kotlinx.android.synthetic.main.content_exercise_approach_data.*
import javax.inject.Inject

class WorkoutExerciseMetricsContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<WorkoutExerciseIndicators>(R.layout.content_exercise_approach_data) {

    override val viewModel: WorkoutExerciseMetricsVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutExerciseMetricsVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reps_panel.exported_decrease_button.setOnClickListener {
            viewModel.decreaseReps()
        }
        reps_panel.exported_increase_button.setOnClickListener {
            viewModel.increaseReps()
        }
        weight_panel.exported_decrease_button.setOnClickListener {
            viewModel.decreaseWeight()
        }
        weight_panel.exported_increase_button.setOnClickListener {
            viewModel.increaseWeight()
        }

        if (savedInstanceState == null) {
            sendMessage(Message.RequestMessage(RequestMessageType.CONTENT_REQUEST), this)
        }
    }

    override fun onReceiveItem(item: WorkoutExerciseIndicators) {
        reps_panel.exported_data_button.text = item.reps.toString()
        weight_panel.exported_data_button.text = item.weight.toString()
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.RequestMessage && message.type == RequestMessageType.CONTENT_REQUEST) {
            viewModel.request()
        }
    }
}