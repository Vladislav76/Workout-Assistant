package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_details.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.communication.MessageSender
import com.vladislavmyasnikov.common.arch.communication.RequestMessageType
import com.vladislavmyasnikov.common.arch.component.VMListFragment
import com.vladislavmyasnikov.common.arch.view.ItemDividerDecoration
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout_creation.WorkoutExerciseCycle
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_details.adapter.WorkoutExerciseCycleAdapter
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_details.viewmodel.WorkoutExerciseCycleListVM
import javax.inject.Inject

class WorkoutExerciseCycleListContent @Inject constructor(
        override val adapter: WorkoutExerciseCycleAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMListFragment<WorkoutExerciseCycle>() {

    override val viewModel by lazy { injectViewModel<WorkoutExerciseCycleListVM>(viewModelFactory) }

    override val itemClickCallback = OnItemClickCallback { id: Long, _: String -> sendMessage(Message.ItemClickMessage(id)) }

    override val itemClickCallbackInSelectMode: OnItemClickCallback? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recycler_view).addItemDecoration(ItemDividerDecoration(5, 10))
        sendMessage(Message.RequestMessage(RequestMessageType.KEY_DATA_REQUEST))
    }

    override fun onReceiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.KeyDataResponseMessage) { viewModel.request(message.id) }
    }
}