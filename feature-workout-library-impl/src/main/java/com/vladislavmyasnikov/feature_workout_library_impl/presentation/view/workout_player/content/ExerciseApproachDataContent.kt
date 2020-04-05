package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_player.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.arch_components.fundamental.VMFragment
import com.vladislavmyasnikov.common.extensions.exported_data_button
import com.vladislavmyasnikov.common.extensions.exported_decrease_button
import com.vladislavmyasnikov.common.extensions.exported_increase_button
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.ExerciseApproachData
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel.ExerciseApproachDataVM
import kotlinx.android.synthetic.main.content_exercise_approach_data.*
import javax.inject.Inject

class ExerciseApproachDataContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<ExerciseApproachData>(R.layout.content_exercise_approach_data) {

    override val viewModel: ExerciseApproachDataVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(ExerciseApproachDataVM::class.java)
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
    }

    override fun onReceiveItem(item: ExerciseApproachData) {
        reps_panel.exported_data_button.text = item.reps.toString()
        weight_panel.exported_data_button.text = item.weight.toString()
    }

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemFetchRequest) {
            viewModel.fetch()
        }
    }
}