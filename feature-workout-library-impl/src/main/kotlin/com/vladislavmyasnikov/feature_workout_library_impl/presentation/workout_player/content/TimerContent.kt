package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.content

import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.arch_components.fundamental.VMFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.TimerValue
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel.WorkoutTimerVM
import kotlinx.android.synthetic.main.content_timer.*
import javax.inject.Inject

class TimerContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<TimerValue>(R.layout.content_timer) {

    override val viewModel: WorkoutTimerVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutTimerVM::class.java)
    }

    override fun onReceiveItem(item: TimerValue) {
        // TODO: add time formatter
        timer.text = String.format("%02d:%02d:%02d", item.hours, item.minutes, item.seconds)
    }

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemFetchRequest) {
            viewModel.fetch()
        }
    }
}