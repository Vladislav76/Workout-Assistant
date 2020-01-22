package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.vladislavmyasnikov.common.legacy.GeneralViewModel
import com.vladislavmyasnikov.common.presentation.ItemDividerDecoration
import com.vladislavmyasnikov.common.utils.convertDpToPixels
import com.vladislavmyasnikov.common.legacy.view.GeneralFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.di.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters.WorkoutSetAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels.ViewModelFactory
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels.WorkoutViewModel
import kotlinx.android.synthetic.main.fragment_workout.*
import javax.inject.Inject

class WorkoutFragment : GeneralFragment<WorkoutViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: WorkoutSetAdapter

    private lateinit var muscleGroupNames: Array<String>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        WorkoutLibraryFeatureComponent.get().workoutScreenComponent.getValue().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WorkoutViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workout_set_list.let {
            it.adapter = adapter
            it.addItemDecoration(ItemDividerDecoration(
                    horizontalDividerThickness = convertDpToPixels(4F, context!!),
                    verticalDividerThickness = convertDpToPixels(0F, context!!))
            )
        }

        if (!viewModel.wasFirstFetchRequest) {
            viewModel.fetchFullWorkoutInfo(arguments!!.getLong(WORKOUT_ID_ARG))
        }
    }

    override fun <Int> onReceiveItem(item: Int) {
        when (item) {
            GeneralViewModel.LOADED_REQUEST_RESULT -> { updateContent() }
        }
    }

    override fun onBackPressed(): Boolean {
        WorkoutLibraryFeatureComponent.get().workoutScreenComponent.resetValue()
        return super.onBackPressed()
    }

    override fun updateToolbar() {
        super.updateToolbar()
        screenTitleController.setTitle("")
        screenTitleController.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)
    }

    private fun updateContent() {
        val workoutInfo = viewModel.workoutInfo
        description_field.text = workoutInfo.description
        adapter.setList(viewModel.workoutInfo.sets)
    }

    companion object {
        private const val WORKOUT_ID_ARG = "workout_id_arg"
        private const val TITLE_ARG = "title_arg"

        fun newInstance(id: Long, title: String): WorkoutFragment {
            return WorkoutFragment().apply {
                arguments = Bundle().apply {
                    putLong(WORKOUT_ID_ARG, id)
                    putString(TITLE_ARG, title)
                }
            }
        }
    }
}