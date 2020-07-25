package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.RequestMessageType
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.interfaces.MessageSender
import com.vladislavmyasnikov.common.arch.fundamental.VMFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExerciseConfig
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel.WorkoutExerciseConfigVM
import kotlinx.android.synthetic.main.content_workout_exercise_config.*
import javax.inject.Inject

class WorkoutExerciseConfigContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<WorkoutExerciseConfig>(R.layout.content_workout_exercise_config) {

    override val viewModel: WorkoutExerciseConfigVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutExerciseConfigVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            sendMessage(Message.RequestMessage(RequestMessageType.CONTENT_REQUEST), this)
        }
    }

    override fun onReceiveItem(item: WorkoutExerciseConfig) {
        val setText = "${resources.getString(R.string.set_label)} ${item.setIndex}/${item.setAmount}"
        set_progress.text = setText
        val exerciseText = "${resources.getString(R.string.exercise_label)} ${item.exerciseIndex}/${item.exerciseAmount}"
        exercise_progress.text = exerciseText
        val approachText = "${resources.getString(R.string.approach_label)} ${item.approachIndex}/${item.approachAmount}"
        approach_progress.text = approachText
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.RequestMessage && message.type == RequestMessageType.CONTENT_REQUEST) {
            viewModel.request()
        }
    }
}