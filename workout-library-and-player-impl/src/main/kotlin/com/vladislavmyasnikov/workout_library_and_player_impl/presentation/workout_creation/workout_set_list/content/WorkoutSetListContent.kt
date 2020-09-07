package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_set_list.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.communication.Messages
import com.vladislavmyasnikov.common.arch.component.VMListFragment
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutSet
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_set_list.adapter.WorkoutSetAdapter
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_set_list.viewmodel.WorkoutSetListVM
import javax.inject.Inject

class WorkoutSetListContent @Inject constructor(
        override val adapter: WorkoutSetAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMListFragment<WorkoutSet>() {

    override val viewModel by lazy { injectViewModel<WorkoutSetListVM>(viewModelFactory) }

    override val itemClickCallback = OnItemClickCallback { id: Long, _: String -> sendMessage(Messages.ItemClickMessage(id)) }

    override val itemClickCallbackInSelectMode: OnItemClickCallback? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.request()
    }
}