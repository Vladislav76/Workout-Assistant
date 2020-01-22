package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.content

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.presentation.view.components.VMListFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ShortExerciseInfo
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.adapters.ExerciseAdapter
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ExerciseListViewModel
import javax.inject.Inject

class ExerciseListFragment @Inject constructor(
        override val bus: SharedBus,
        override val adapter: ExerciseAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMListFragment<ShortExerciseInfo>() {

    override val label = "exercise_list_fragment"

    override lateinit var viewModel: ExerciseListViewModel

    override val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            bus.sendPacket(Packet.ItemClickMessage(id))
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ExerciseListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch()
    }
}