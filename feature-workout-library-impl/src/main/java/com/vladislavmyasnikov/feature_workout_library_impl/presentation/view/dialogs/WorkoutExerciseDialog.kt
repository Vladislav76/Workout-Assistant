package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.dialogs

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.arch_components.fundamental.VMDialog
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel.WorkoutExerciseVM
import kotlinx.android.synthetic.main.content_workout_exercise_details.*
import javax.inject.Inject

class WorkoutExerciseDialog @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMDialog<WorkoutExercise>(R.layout.content_workout_exercise_details) {

    companion object {
        private const val ARG_WORKOUT_EXERCISE_ID = "workout_exercise_id"
    }

    override val label = ""

    override val viewModel: WorkoutExerciseVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutExerciseVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.fetch(requireArguments().getLong(ARG_WORKOUT_EXERCISE_ID))
        }
    }

    override fun onReceiveItem(item: WorkoutExercise) {
        val resID = requireContext().resources.getIdentifier(item.info.avatarID, "drawable", requireContext().packageName)
        workout_exercise_icon.setImageDrawable(ContextCompat.getDrawable(requireContext(), resID))
        workout_exercise_title.text = item.info.title
        workout_exercise_reps.text = item.exerciseApproachData.reps.toString()
        workout_exercise_weight.text = item.exerciseApproachData.weight.toString()
    }

    fun putArguments(id: Long) {
        arguments = Bundle().apply {
            putLong(ARG_WORKOUT_EXERCISE_ID, id)
        }
    }
}