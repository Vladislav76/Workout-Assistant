package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.component.VMFragment
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_execution.TimerValue
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.viewmodel.WorkoutTimerVM
import kotlinx.android.synthetic.main.content_timer.*
import javax.inject.Inject

class TimerContent @Inject constructor(
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<TimerValue>(R.layout.content_timer) {

    override val viewModel by lazy { injectViewModel<WorkoutTimerVM>(viewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.request()
    }

    override fun onReceiveItem(item: TimerValue) {
        // TODO: add time formatter
        timer.text = String.format("%02d:%02d:%02d", item.hours, item.minutes, item.seconds)
    }
}