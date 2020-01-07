package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.content

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.vladislavmyasnikov.common.experimental.view.components.ToolbarFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.di.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.ShortExerciseInfo
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.ExerciseListViewModel
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class ExerciseListToolbarFragment : ToolbarFragment<List<ShortExerciseInfo>>() {

    companion object {
        fun newInstance() = ExerciseListToolbarFragment()
    }

    override val label = "exercise_list_toolbar_fragment"

    private val onActionClickCallback = Toolbar.OnMenuItemClickListener { item: MenuItem ->
        when (item.itemId) {
            R.id.filter_exercise_list_action -> {
                // show filter dialog
                debugMessage("R.id.filter_exercise_list_action")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override lateinit var viewModel: ExerciseListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ExerciseLibraryFeatureComponent.get().exerciseListScreenComponent.getValue().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExerciseListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.exercise_list_screen_title)
        addMenu(R.menu.menu_exercise_list)
        addMenuListener(onActionClickCallback)
    }
}

/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            FILTER_EXERCISE_LIST_REQUEST_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    adapter.filterListByMuscleGroups(ExerciseFilterFragment.extractData(data!!))
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    adapter.resetFiltering()
                }
            }
        }
    }

    private fun showFilterDialog() {
        val dialog = ExerciseFilterFragment.newInstance(adapter.selectedMuscleGroupsIDs)
        dialog.setTargetFragment(this, FILTER_EXERCISE_LIST_REQUEST_CODE)
        dialog.show(fragmentManager!!, EXERCISE_FILTER_DIALOG_TAG)
    }

    companion object {
        private const val FILTER_EXERCISE_LIST_REQUEST_CODE = 1
        private const val EXERCISE_FILTER_DIALOG_TAG = "exercise_filter_dialog"
    }
 */