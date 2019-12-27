package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.common.components.GeneralViewModel
import com.vladislavmyasnikov.common.components.ItemDividerDecoration
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.utils.convertDpToPixels
import com.vladislavmyasnikov.common.view.GeneralFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.di.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.Screens
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters.WorkoutAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels.ViewModelFactory
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels.WorkoutListViewModel
import javax.inject.Inject

class WorkoutListFragment : GeneralFragment<WorkoutListViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: WorkoutAdapter

    private val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            router.navigateTo(Screens.WorkoutDetailsScreen(id, title))
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        WorkoutLibraryFeatureComponent.get().inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WorkoutListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.linear_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!adapter.hasObservers()) {
            adapter.setHasStableIds(true)
            adapter.callback = itemClickCallback
        }
        view.findViewById<RecyclerView>(R.id.recycler_view).let {
            it.adapter = adapter
            it.addItemDecoration(ItemDividerDecoration(
                    horizontalDividerThickness = convertDpToPixels(8F, context!!),
                    verticalDividerThickness = convertDpToPixels(16F, context!!))
            )
        }
    }

    override fun <Int> onReceiveItem(item: Int) {
        when (item) {
            GeneralViewModel.LOADED_REQUEST_RESULT -> { adapter.setList(viewModel.workoutsInfo) }
        }
    }

    override fun updateToolbar() {
        super.updateToolbar()
        screenTitleController.setTitle(R.string.workout_list_screen_title)
        screenTitleController.setDisplayHomeAsUpEnabled(false)
    }

    companion object {
        fun newInstance() = WorkoutListFragment()
    }
}
