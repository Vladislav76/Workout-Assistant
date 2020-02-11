package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.content

import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.Event
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.presentation.view.components.VMListFragment
import com.vladislavmyasnikov.common.presentation.view.components.VMNewListFragment
import com.vladislavmyasnikov.feature_workout_library_impl.domain.FullWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters.WorkoutExerciseAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels.WorkoutExerciseListVM
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels.WorkoutVM
import javax.inject.Inject

class WorkoutExerciseListContent @Inject constructor(
        override val bus: SharedBus,
        override val adapter: WorkoutExerciseAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMListFragment<WorkoutExercise>() {

    override val label = "WORKOUT_EXERCISE_LIST_CF"

    override val viewModel: WorkoutExerciseListVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutExerciseListVM::class.java)
    }

    override val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            bus.sendPacket(Packet.ItemClickMessage(id))
        }
    }

    override val itemClickCallbackInSelectMode: OnItemClickCallback? = null

    override fun onReceiveEvent(event: Event) {
        if (event is Event.SelectedItemIdChangeEvent) {
            adapter.workoutSetApproach = event.id.toInt()
        }
    }
}