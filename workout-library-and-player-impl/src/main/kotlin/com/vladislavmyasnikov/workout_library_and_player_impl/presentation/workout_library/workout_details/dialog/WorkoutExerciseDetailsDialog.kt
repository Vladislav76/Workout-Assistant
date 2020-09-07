package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.dialog

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.component.VMDialog
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExercise
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_execution.workout_player.viewmodel.WorkoutExerciseVM
import kotlinx.android.synthetic.main.content_workout_exercise_details.*
import javax.inject.Inject

class WorkoutExerciseDetailsDialog @Inject constructor(
        override val viewModelFactory: ViewModelProvider.Factory
) : VMDialog<WorkoutExercise>(R.layout.content_workout_exercise_details) {

    companion object {
        private const val ARG_WORKOUT_EXERCISE_ID = "workout_exercise_id"
    }

    override val viewModel by lazy { injectViewModel<WorkoutExerciseVM>(viewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.request(requireArguments().getLong(ARG_WORKOUT_EXERCISE_ID))
    }

    override fun onReceiveItem(item: WorkoutExercise) {
        workout_exercise_title.text = item.info.title
        workout_exercise_reps.text = item.workoutExerciseIndicators.reps.toString()
        workout_exercise_weight.text = item.workoutExerciseIndicators.weight.toString()

        requireContext().let { context ->
            val resID = context.resources.getIdentifier(item.info.avatarID, "drawable", context.packageName)
            workout_exercise_icon.setImageDrawable(ContextCompat.getDrawable(context, resID))
        }
    }

    fun putArguments(id: Long) {
        arguments = Bundle().apply {
            putLong(ARG_WORKOUT_EXERCISE_ID, id)
        }
    }
}