package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_exercise_details.dialog

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.component.VMDialog
import com.vladislavmyasnikov.common.extensions.exported_data_button
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutExerciseCycle
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.textOfReps
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.textOfWeight
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_exercise_details.viewmodel.WorkoutExerciseCycleVM
import kotlinx.android.synthetic.main.content_workout_exercise_cycle.*
import javax.inject.Inject

class WorkoutExerciseCycleDialog @Inject constructor(
        override val viewModelFactory: ViewModelProvider.Factory
) : VMDialog<WorkoutExerciseCycle>(R.layout.content_workout_exercise_cycle) {

    private companion object {
        const val ARG_WORKOUT_EXERCISE_CYCLE_ID = "workout_exercise_cycle_id"
    }

    override val viewModel by lazy { injectViewModel<WorkoutExerciseCycleVM>(viewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.request(requireArguments().getLong(ARG_WORKOUT_EXERCISE_CYCLE_ID))
    }

    override fun onReceiveItem(item: WorkoutExerciseCycle) {
        reps_panel.exported_data_button.text = item.textOfReps()
        weight_panel.exported_data_button.text = item.textOfWeight()
    }

    fun putArguments(id: Long) {
        arguments = Bundle().apply {
            putLong(ARG_WORKOUT_EXERCISE_CYCLE_ID, id)
        }
    }
}