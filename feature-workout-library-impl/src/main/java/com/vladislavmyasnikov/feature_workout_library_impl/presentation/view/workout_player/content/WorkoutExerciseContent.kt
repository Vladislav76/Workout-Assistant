package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_player.content

import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.arch_components.fundamental.VMFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel.WorkoutExerciseVM
import kotlinx.android.synthetic.main.content_workout_exercise_details.*
import javax.inject.Inject

class WorkoutExerciseContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<WorkoutExercise>(R.layout.content_workout_exercise_details) {

    override val label = "WORKOUT_EXERCISE_CF"

    override val viewModel: WorkoutExerciseVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutExerciseVM::class.java)
    }

    override fun onReceiveItem(item: WorkoutExercise) {
        val resID = requireContext().resources.getIdentifier(item.info.avatarID, "drawable", requireContext().packageName)
        workout_exercise_icon.setImageDrawable(ContextCompat.getDrawable(requireContext(), resID))
        workout_exercise_title.text = item.info.title
        workout_exercise_reps.text = item.exerciseApproachData.reps.toString()
        workout_exercise_weight.text = item.exerciseApproachData.weight.toString()
    }

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemFetchRequest) {
            viewModel.fetch()
        }
    }
}