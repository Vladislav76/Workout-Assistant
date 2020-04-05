package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_details.content

import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.presentation.view.components.VMListFragment
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters.WorkoutExerciseAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel.WorkoutExerciseListVM
import javax.inject.Inject

class WorkoutExerciseListContent @Inject constructor(
        override val bus: SharedBus,
        override val adapter: WorkoutExerciseAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMListFragment<WorkoutExercise>() {

    override val viewModel: WorkoutExerciseListVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutExerciseListVM::class.java)
    }

    override val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            bus.sendPacket(Packet.ItemClickMessage(id))
        }
    }

    override val itemClickCallbackInSelectMode: OnItemClickCallback? = null

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemFetchRequest) {
            viewModel.fetch(packet.id)
        }
    }
}