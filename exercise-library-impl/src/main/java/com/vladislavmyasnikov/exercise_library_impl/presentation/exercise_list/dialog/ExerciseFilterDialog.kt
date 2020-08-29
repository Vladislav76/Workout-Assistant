package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.component.VMDialog
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.exercise_library_impl.R
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.viewmodel.MuscleGroupListVM
import kotlinx.android.synthetic.main.dialog_exercise_filter.*
import javax.inject.Inject

class ExerciseFilterDialog @Inject constructor(
        override val viewModelFactory: ViewModelProvider.Factory
) : VMDialog<Pair<List<String>, List<Boolean>>>(R.layout.dialog_exercise_filter) {

    override val viewModel by lazy { injectViewModel<MuscleGroupListVM>(viewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        muscle_groups.setOnChipCheckedListener { position, isChecked -> viewModel.selectMuscleGroup(position, isChecked) }
        apply_button.setOnClickListener { viewModel.apply(); dismiss() }
        cancel_button.setOnClickListener { viewModel.cancel(); dismiss() }
        clear_button.setOnClickListener { viewModel.clearAll(); dismiss() }
        viewModel.request()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        viewModel.cancel()
    }

    override fun onReceiveItem(item: Pair<List<String>, List<Boolean>>) {
        muscle_groups.setItems(item.first, item.second)
    }
}