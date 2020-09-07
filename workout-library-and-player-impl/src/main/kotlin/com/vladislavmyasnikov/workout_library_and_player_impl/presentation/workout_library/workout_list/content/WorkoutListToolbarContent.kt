package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_list.content

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.communication.Messages
import com.vladislavmyasnikov.common.arch.component.VMToolbarFragment
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout.ShortWorkout
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_list.viewmodel.WorkoutListVM
import javax.inject.Inject

class WorkoutListToolbarContent @Inject constructor(
        override val viewModelFactory: ViewModelProvider.Factory
) : VMToolbarFragment<List<ShortWorkout>>() {

    override val viewModel by lazy { injectViewModel<WorkoutListVM>(viewModelFactory) }

    private val onActionClickCallback = Toolbar.OnMenuItemClickListener { item: MenuItem ->
        when (item.itemId) {
            R.id.create_workout_action -> {
                sendMessage(Messages.TransitionRequestMessage)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.workout_list_screen_title)
        addMenu(R.menu.workout_list_actions)
        addMenuListener(onActionClickCallback)
    }
}