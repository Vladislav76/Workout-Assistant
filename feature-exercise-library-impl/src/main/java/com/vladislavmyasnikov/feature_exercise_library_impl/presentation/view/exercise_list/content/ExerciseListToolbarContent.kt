package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.exercise_list.content

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.components.VMToolbarFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ShortExerciseInfo
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.dialogs.ExerciseFilterFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodel.ExerciseListVM
import javax.inject.Inject

class ExerciseListToolbarContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMToolbarFragment<List<ShortExerciseInfo>>() {

    companion object {
        private const val FILTER_EXERCISE_LIST_CODE = 1
    }

    override val viewModel: ExerciseListVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(ExerciseListVM::class.java)
    }

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
                    viewModel.filter(ExerciseFilterFragment.extractData(data!!).toList())
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    viewModel.filter(emptyList())
                }
            }
        }
    }

    private fun showFilterDialog() {
        val dialog = ExerciseFilterFragment.newInstance(viewModel.filteredMuscleGroupIDs.toIntArray())
        dialog.setTargetFragment(this, FILTER_EXERCISE_LIST_CODE)
        dialog.show(fragmentManager!!, null)
    }
}