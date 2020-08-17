package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.RequestMessageType
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.interfaces.MessageSender
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import com.vladislavmyasnikov.common.presentation.view.components.VMListFragment
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.entity.workout.ShortWorkout
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.adapter.WorkoutAdapter
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.viewmodel.WorkoutListVM
import javax.inject.Inject

class WorkoutListContent @Inject constructor(
        override val bus: SharedBus,
        override val adapter: WorkoutAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMListFragment<ShortWorkout>() {

    override val viewModel by lazy { injectViewModel<WorkoutListVM>(viewModelFactory) }

    override val itemClickCallback = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
            sendMessage(Message.ItemClickMessage(id))
        }
    }

    override val itemClickCallbackInSelectMode: OnItemClickCallback? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            sendMessage(Message.RequestMessage(RequestMessageType.CONTENT_REQUEST), this)
        }
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.RequestMessage && message.type == RequestMessageType.CONTENT_REQUEST) {
            viewModel.request()
        }
    }
}

//            view.findViewById<RecyclerView>(R.id.recycler_view).addItemDecoration(ItemDividerDecoration(
//                    horizontalDividerThickness = convertDpToPixels(8F, context!!),
//                    verticalDividerThickness = convertDpToPixels(16F, context!!))
//            )
