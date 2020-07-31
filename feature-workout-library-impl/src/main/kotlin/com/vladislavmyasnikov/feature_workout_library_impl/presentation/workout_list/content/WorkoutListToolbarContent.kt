package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_list.content

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.presentation.view.components.VMToolbarFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.entity.ShortWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_list.viewmodel.WorkoutListVM
import javax.inject.Inject

class WorkoutListToolbarContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMToolbarFragment<List<ShortWorkout>>() {

    override val viewModel: WorkoutListVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutListVM::class.java)
    }

    private val onActionClickCallback = Toolbar.OnMenuItemClickListener { item: MenuItem ->
        when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
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