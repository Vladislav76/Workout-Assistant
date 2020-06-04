package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.arch_components.fundamental.VMFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutProcessState
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel.WorkoutPlayerVM
import kotlinx.android.synthetic.main.content_control_panel.*
import javax.inject.Inject

class ControlPanelContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<WorkoutProcessState>(R.layout.content_control_panel) {

    override val viewModel: WorkoutPlayerVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutPlayerVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next_button.setOnClickListener { viewModel.next() }
        stop_button.setOnClickListener { viewModel.stop() }
        pause_button.setOnClickListener { viewModel.pause() }
        resume_button.setOnClickListener { viewModel.resume() }
    }

    override fun onReceiveItem(item: WorkoutProcessState) {
        when (item) {
            WorkoutProcessState.STARTED -> {
                resume_button.visibility = View.GONE
                pause_button.visibility = View.VISIBLE
            }
            WorkoutProcessState.PAUSED -> {
                resume_button.visibility = View.VISIBLE
                pause_button.visibility = View.GONE
            }
            WorkoutProcessState.FINISHED -> {
                // TODO: show warning dialog
                // TODO: move to 'Workout results' screen
                resume_button.visibility = View.GONE
                pause_button.visibility = View.GONE
                stop_button.visibility = View.GONE
                next_button.visibility = View.GONE
            }
        }
    }

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemFetchRequest) {
            viewModel.start(packet.id)
        }
    }
}