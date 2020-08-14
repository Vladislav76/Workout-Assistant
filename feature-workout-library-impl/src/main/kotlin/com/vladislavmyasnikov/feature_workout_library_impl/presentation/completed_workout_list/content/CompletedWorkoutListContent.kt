package com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_list.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.presentation.view.components.VMListFragment
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.completed_workout.ShortCompletedWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_list.adapter.CompletedWorkoutAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_list.viewmodel.CompletedWorkoutListVM
import javax.inject.Inject

class CompletedWorkoutListContent @Inject constructor(
        override val bus: SharedBus,
        override val adapter: CompletedWorkoutAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
): VMListFragment<ShortCompletedWorkout>() {

    override val viewModel by lazy { injectViewModel<CompletedWorkoutListVM>(viewModelFactory) }

    override val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            sendMessage(Message.ItemClickMessage(id))
        }
    }

    override val itemClickCallbackInSelectMode = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.request()
    }
}