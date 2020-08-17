package com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.content

import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.presentation.view.components.VMToolbarFragment
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.viewmodel.CompletedWorkoutVM
import com.vladislavmyasnikov.workout_library_and_player_api.domain.entity.CompletedWorkout
import javax.inject.Inject

class CompletedWorkoutToolbarContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMToolbarFragment<CompletedWorkout>() {

    override val viewModel by lazy { injectViewModel<CompletedWorkoutVM>(viewModelFactory) }
}