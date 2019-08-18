package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.core_components.interfaces.OnBackPressedListener
import com.vladislavmyasnikov.core_components.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.core_components.view.GeneralFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.di.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.adapters.ExerciseAdapter
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ExerciseListViewModel
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class ExerciseListFragment : GeneralFragment<ExerciseListViewModel>(), OnBackPressedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: ExerciseAdapter

    private val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            val fragment = ExerciseFragment.newInstance(id, title)
            fragmentController.addFragmentOnTop(fragment)
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
        updateTitle()
        screenTitleController.setDisplayHomeAsUpEnabled(false)

        view.findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter

        if (savedInstanceState == null) {
            adapter.callback = itemClickCallback
            viewModel.fetchShortExercisesInfo()
        }
    }

    override fun <Int> onReceiveItem(item: Int) {
        when (item) {
            GeneralViewModel.LOADED_REQUEST_RESULT -> { adapter.updateList(viewModel.exercisesInfo) }
        }
    }

    override fun onBackPressed(): Boolean {
        ExerciseLibraryFeatureComponent.get().exerciseListScreenComponent.resetValue()
        return false
    }

    private fun updateTitle() {
        screenTitleController.setTitle(R.string.exercise_list_screen_title)
    }

    companion object {
        fun newInstance() = ExerciseListFragment()
    }
}
