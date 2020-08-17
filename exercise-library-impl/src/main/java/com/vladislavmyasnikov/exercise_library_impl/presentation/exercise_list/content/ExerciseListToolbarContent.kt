package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.content

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.presentation.view.components.VMToolbarFragment
import com.vladislavmyasnikov.exercise_library_impl.R
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.ShortExercise
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.dialog.ExerciseFiltrationDialog
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.viewmodel.ExerciseListVM
import javax.inject.Inject

class ExerciseListToolbarContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMToolbarFragment<List<ShortExercise>>() {

    companion object {
        private const val FILTER_EXERCISE_LIST_CODE = 1
    }

    override val viewModel by lazy { injectViewModel<ExerciseListVM>(viewModelFactory) }

    private val onActionClickCallback = Toolbar.OnMenuItemClickListener { item: MenuItem ->
        when (item.itemId) {
            R.id.filter_exercise_list_action -> {
                showFilterDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.exercise_list_screen_title)
        addMenu(R.menu.menu_exercise_list)
        addMenuListener(onActionClickCallback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            FILTER_EXERCISE_LIST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    viewModel.filter(ExerciseFiltrationDialog.extractData(data!!).toList())
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    viewModel.filter(emptyList())
                }
            }
        }
    }

    private fun showFilterDialog() {
        val dialog = ExerciseFiltrationDialog.newInstance(viewModel.filteredMuscleGroupIDs.toIntArray())
        dialog.setTargetFragment(this, FILTER_EXERCISE_LIST_CODE)
        dialog.show(fragmentManager!!, null)
    }
}