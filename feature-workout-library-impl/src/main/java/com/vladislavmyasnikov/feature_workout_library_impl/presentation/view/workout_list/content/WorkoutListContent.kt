package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.presentation.view.components.VMListFragment
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.ShortWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters.WorkoutAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel.WorkoutListVM
import javax.inject.Inject

class WorkoutListContent @Inject constructor(
        override val bus: SharedBus,
        override val adapter: WorkoutAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMListFragment<ShortWorkout>() {

    override val label = "WORKOUT_LIST_CF"

    override val viewModel: WorkoutListVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutListVM::class.java)
    }

    override val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            bus.sendPacket(Packet.ItemClickMessage(id))
        }
    }

    override val itemClickCallbackInSelectMode: OnItemClickCallback? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch()
    }
}

//            view.findViewById<RecyclerView>(R.id.recycler_view).addItemDecoration(ItemDividerDecoration(
//                    horizontalDividerThickness = convertDpToPixels(8F, context!!),
//                    verticalDividerThickness = convertDpToPixels(16F, context!!))
//            )
