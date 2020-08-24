package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.arch.component.VMListFragment
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.ShortExercise
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.adapter.ExerciseAdapter
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.viewmodel.ExerciseListVM
import javax.inject.Inject

class ExerciseListContent @Inject constructor(
        override val adapter: ExerciseAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMListFragment<ShortExercise>() {

    override val viewModel by lazy { injectViewModel<ExerciseListVM>(viewModelFactory) }

    override val itemClickCallback = OnItemClickCallback { id: Long, _: String -> sendMessage(Message.ItemClickMessage(id)) }

    override val itemClickCallbackInSelectMode: OnItemClickCallback? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.request()
    }
}