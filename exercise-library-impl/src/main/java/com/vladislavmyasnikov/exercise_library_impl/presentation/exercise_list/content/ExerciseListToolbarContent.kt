package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.content

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.communication.RequestMessageType
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.arch.component.VMToolbarFragment
import com.vladislavmyasnikov.exercise_library_impl.R
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.ShortExercise
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.viewmodel.ExerciseListVM
import javax.inject.Inject

class ExerciseListToolbarContent @Inject constructor(
        override val viewModelFactory: ViewModelProvider.Factory
) : VMToolbarFragment<List<ShortExercise>>() {

    override val viewModel by lazy { injectViewModel<ExerciseListVM>(viewModelFactory) }

    private val onActionClickCallback = Toolbar.OnMenuItemClickListener { item: MenuItem ->
        when (item.itemId) {
            R.id.filter_exercise_list_action -> {
                sendMessage(Message.RequestMessage(RequestMessageType.TRANSITION_REQUEST))
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
}