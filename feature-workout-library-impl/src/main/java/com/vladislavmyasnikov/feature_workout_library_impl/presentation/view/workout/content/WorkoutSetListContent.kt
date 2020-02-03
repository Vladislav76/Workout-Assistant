package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.content

import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.presentation.view.components.VMNewListFragment
import com.vladislavmyasnikov.feature_workout_library_impl.domain.FullWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutSet
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters.WorkoutSetAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels.WorkoutVM
import javax.inject.Inject

class WorkoutSetListContent @Inject constructor(
        override val bus: SharedBus,
        override val adapter: WorkoutSetAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMNewListFragment<WorkoutSet, FullWorkout>() {

    override val label = "WORKOUT_SET_LIST_CF"

    override val viewModel: WorkoutVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutVM::class.java)
    }

    override val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            // open set's dialog
        }
    }

    override val itemClickCallbackInSelectMode: OnItemClickCallback? = null
}
