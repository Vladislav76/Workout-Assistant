package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.content

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.RequestMessageType
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.interfaces.MessageSender
import com.vladislavmyasnikov.common.arch.fundamental.VMFragment
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel.WorkoutExerciseVM
import kotlinx.android.synthetic.main.content_workout_exercise_details.*
import javax.inject.Inject

class WorkoutExerciseContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<WorkoutExercise>(R.layout.content_workout_exercise_details) {

    override val viewModel by lazy { injectViewModel<WorkoutExerciseVM>(viewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            sendMessage(Message.RequestMessage(RequestMessageType.CONTENT_REQUEST), this)
        }
    }

    override fun onReceiveItem(item: WorkoutExercise) {
        val resID = requireContext().resources.getIdentifier(item.info.avatarID, "drawable", requireContext().packageName)
        workout_exercise_icon.setImageDrawable(ContextCompat.getDrawable(requireContext(), resID))
        workout_exercise_title.text = item.info.title
        workout_exercise_reps.text = item.workoutExerciseIndicators.reps.toString()
        workout_exercise_weight.text = item.workoutExerciseIndicators.weight.toString()
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.RequestMessage && message.type == RequestMessageType.CONTENT_REQUEST) {
            viewModel.request()
        }
    }
}