package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.content

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.components.VMToolbarFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.ShortWorkoutInfo
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels.WorkoutListVM
import javax.inject.Inject

class WorkoutListToolbarContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMToolbarFragment<List<ShortWorkoutInfo>>() {

    override val label = "WORKOUT_LIST_TOOLBAR_CF"

    override lateinit var viewModel: WorkoutListVM

    private val onActionClickCallback = Toolbar.OnMenuItemClickListener { item: MenuItem ->
        when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, viewModelFactory).get(WorkoutListVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.workout_list_screen_title)
//        addMenu(R.menu.menu_exercise_list)
        addMenuListener(onActionClickCallback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // will be later...
    }
}