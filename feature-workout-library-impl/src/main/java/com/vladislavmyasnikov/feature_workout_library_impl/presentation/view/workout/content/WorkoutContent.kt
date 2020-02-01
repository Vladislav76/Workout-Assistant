package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.content

import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.VMFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.FullWorkoutInfo
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels.WorkoutVM
import kotlinx.android.synthetic.main.content_workout_details.*
import javax.inject.Inject

class WorkoutContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<FullWorkoutInfo>(R.layout.content_workout_details) {

    override val label = "WORKOUT_CF"

    override val viewModel: WorkoutVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutVM::class.java)
    }

    override fun onReceiveItem(item: FullWorkoutInfo) {
        workout_description.text = item.description
    }

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemClickMessage) {
            viewModel.fetch(packet.id)
            bus.sendPacket(Packet.EmptyMessage())
        } else super.onReceivePacket(packet)
    }
}