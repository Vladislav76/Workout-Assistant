package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.dialogs

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.arch_components.fundamental.VMDialog
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels.WorkoutExerciseListVM
import kotlinx.android.synthetic.main.content_workout_exercise_details.*
import javax.inject.Inject

class WorkoutExerciseDialog @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMDialog<List<WorkoutExercise>>(R.layout.content_workout_exercise_details) {

    companion object {
        private const val ARG_WORKOUT_EXERCISE_ID = "workout_exercise_id"
    }

    override val label = "WORKOUT_EXERCISE_DF"

    override val viewModel: WorkoutExerciseListVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutExerciseListVM::class.java)
    }

    override fun onReceiveItem(_item: List<WorkoutExercise>) {
        val workoutExerciseID = arguments!!.getLong(ARG_WORKOUT_EXERCISE_ID)
        val item = viewModel.fetchWorkoutExercise(workoutExerciseID)

        val resID = requireContext().resources.getIdentifier(item.avatarID, "drawable", requireContext().packageName)
        workout_exercise_icon.setImageDrawable(ContextCompat.getDrawable(requireContext(), resID))

        workout_exercise_title.text = item.title
        workout_exercise_reps.text = item.reps[viewModel.workoutSetApproach].toString()
        workout_exercise_weight.text = item.weights[viewModel.workoutSetApproach].toString()
    }

    fun putArguments(id: Long) {
        arguments = Bundle().apply {
            putLong(ARG_WORKOUT_EXERCISE_ID, id)
        }
    }
}