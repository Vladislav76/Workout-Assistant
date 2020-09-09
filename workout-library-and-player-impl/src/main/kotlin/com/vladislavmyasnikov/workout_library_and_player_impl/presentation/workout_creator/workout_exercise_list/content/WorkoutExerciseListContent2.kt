package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_exercise_list.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.communication.MessageSender
import com.vladislavmyasnikov.common.arch.communication.Messages
import com.vladislavmyasnikov.common.arch.component.VMListFragment
import com.vladislavmyasnikov.common.arch.view.ItemDividerDecoration
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutExercise
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_exercise_list.adapter.WorkoutExerciseAdapter2
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_exercise_list.viewmodel.WorkoutExerciseListVM2
import javax.inject.Inject

class WorkoutExerciseListContent2 @Inject constructor(
        override val adapter: WorkoutExerciseAdapter2,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMListFragment<WorkoutExercise>() {

    override val viewModel by lazy { injectViewModel<WorkoutExerciseListVM2>(viewModelFactory) }

    override val itemClickCallback = OnItemClickCallback { id: Long, _: String -> sendMessage(Messages.ItemClickMessage(id)) }

    override val itemClickCallbackInSelectMode: OnItemClickCallback? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recycler_view).addItemDecoration(ItemDividerDecoration(20, 10))
        sendMessage(Messages.KeyDataRequestMessage)
    }

    override fun onReceiveMessage(message: Message, sender: MessageSender) {
        if (message is Messages.KeyDataResponseMessage) { viewModel.request(message.id) }
    }
}