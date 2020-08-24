package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.component.VMFragment
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.WorkoutExerciseConfig
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.viewmodel.WorkoutExerciseConfigVM
import kotlinx.android.synthetic.main.content_workout_exercise_config.*
import javax.inject.Inject

class WorkoutExerciseConfigContent @Inject constructor(
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<WorkoutExerciseConfig>(R.layout.content_workout_exercise_config) {

    override val viewModel by lazy { injectViewModel<WorkoutExerciseConfigVM>(viewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.request()
    }

    override fun onReceiveItem(item: WorkoutExerciseConfig) {
        val setText = "${resources.getString(R.string.set_label)} ${item.setIndex}/${item.setAmount}"
        set_progress.text = setText
        val exerciseText = "${resources.getString(R.string.exercise_label)} ${item.exerciseIndex}/${item.exerciseAmount}"
        exercise_progress.text = exerciseText
        val approachText = "${resources.getString(R.string.approach_label)} ${item.approachIndex}/${item.approachAmount}"
        approach_progress.text = approachText
    }
}