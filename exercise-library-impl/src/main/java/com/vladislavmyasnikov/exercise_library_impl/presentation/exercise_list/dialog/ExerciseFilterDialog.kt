package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.dialog

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.VMDialog
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.exercise_library_impl.R
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.viewmodel.MuscleGroupListVM
import kotlinx.android.synthetic.main.dialog_exercise_filter.*
import javax.inject.Inject

class ExerciseFilterDialog @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMDialog<List<Pair<String,Boolean>>>(R.layout.dialog_exercise_filter) {

    override val viewModel by lazy { injectViewModel<MuscleGroupListVM>(viewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        muscle_groups.setOnChipCheckedListener { position, isChecked -> viewModel.checkItem(position, isChecked) }
        apply_button.setOnClickListener { viewModel.apply(); dismiss() }
        viewModel.request()
    }

    override fun onReceiveItem(item: List<Pair<String, Boolean>>) {
        muscle_groups.setItems(item)
    }
}