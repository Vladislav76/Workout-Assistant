package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.dialog

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.VMDialog
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExercise
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.viewmodel.WorkoutExerciseVM
import kotlinx.android.synthetic.main.content_workout_exercise_details.*
import javax.inject.Inject

class WorkoutExerciseDetailsDialog @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMDialog<WorkoutExercise>(R.layout.content_workout_exercise_details) {

    companion object {
        private const val ARG_WORKOUT_EXERCISE_ID = "workout_exercise_id"
    }

    override val viewModel: WorkoutExerciseVM by lazy { injectViewModel<WorkoutExerciseVM>(viewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.request(requireArguments().getLong(ARG_WORKOUT_EXERCISE_ID))
        }
    }

    override fun onReceiveItem(item: WorkoutExercise) {
        val resID = requireContext().resources.getIdentifier(item.info.avatarID, "drawable", requireContext().packageName)
        workout_exercise_icon.setImageDrawable(ContextCompat.getDrawable(requireContext(), resID))
        workout_exercise_title.text = item.info.title
        workout_exercise_reps.text = item.workoutExerciseIndicators.reps.toString()
        workout_exercise_weight.text = item.workoutExerciseIndicators.weight.toString()
    }

    fun putArguments(id: Long) {
        arguments = Bundle().apply {
            putLong(ARG_WORKOUT_EXERCISE_ID, id)
        }
    }
}