package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.core_components.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.core_components.view.GeneralFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.di.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.Screens
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.adapters.ExerciseAdapter
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ExerciseListViewModel
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class ExerciseListFragment : GeneralFragment<ExerciseListViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: ExerciseAdapter

    private val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            router.navigateTo(Screens.ExerciseDetailsScreen(id, title))
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        ExerciseLibraryFeatureComponent.get().exerciseListScreenComponent.getValue().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExerciseListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter
        adapter.callback = itemClickCallback
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_exercise_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_exercise_list_action -> {
                showFilterDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

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

    override fun <Int> onReceiveItem(item: Int) {
        when (item) {
            GeneralViewModel.LOADED_REQUEST_RESULT -> adapter.setList(viewModel.exercisesInfo)
        }
    }

    override fun onBackPressed(): Boolean {
        ExerciseLibraryFeatureComponent.get().exerciseListScreenComponent.resetValue()
        return super.onBackPressed()
    }

    override fun updateToolbar() {
        super.updateToolbar()
        screenTitleController.setTitle(R.string.exercise_list_screen_title)
        screenTitleController.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)
    }

    private fun showFilterDialog() {
        val dialog = ExerciseFilterFragment.newInstance(adapter.selectedMuscleGroupsIDs)
        dialog.setTargetFragment(this, FILTER_EXERCISE_LIST_REQUEST_CODE)
        dialog.show(fragmentManager, EXERCISE_FILTER_DIALOG_TAG)
    }

    companion object {
        private const val FILTER_EXERCISE_LIST_REQUEST_CODE = 1
        private const val EXERCISE_FILTER_DIALOG_TAG = "exercise_filter_dialog"

        fun newInstance() = ExerciseListFragment()
    }
}
