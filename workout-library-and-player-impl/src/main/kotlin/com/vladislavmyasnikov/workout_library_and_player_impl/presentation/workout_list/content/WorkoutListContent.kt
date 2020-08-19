package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.presentation.ItemDividerDecoration
import com.vladislavmyasnikov.common.presentation.view.components.VMListFragment
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout.ShortWorkout
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.adapter.WorkoutAdapter
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.viewmodel.WorkoutListVM
import javax.inject.Inject

class WorkoutListContent @Inject constructor(
        override val bus: SharedBus,
        override val adapter: WorkoutAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMListFragment<ShortWorkout>() {

    override val viewModel by lazy { injectViewModel<WorkoutListVM>(viewModelFactory) }

    override val itemClickCallback = OnItemClickCallback { id: Long, _: String -> sendMessage(Message.ItemClickMessage(id)) }

    override val itemClickCallbackInSelectMode: OnItemClickCallback? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recycler_view).addItemDecoration( // TODO: add to API
                ItemDividerDecoration(
                        horizontalDividerThickness = 8, // TODO: convert to dp
                        verticalDividerThickness = 16, // TODO: convert to dp
                )
        )
        viewModel.request()
    }
}