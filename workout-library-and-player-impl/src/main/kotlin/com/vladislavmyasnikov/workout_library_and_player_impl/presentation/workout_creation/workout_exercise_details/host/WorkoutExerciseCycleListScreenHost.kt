package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_details.host

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.communication.MessageReceiver
import com.vladislavmyasnikov.common.arch.communication.MessageSender
import com.vladislavmyasnikov.common.arch.communication.Messages
import com.vladislavmyasnikov.common.arch.component.HostFragment
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.WorkoutFeatureComponent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.Dialogs
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.NavigationComponentStore
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_details.content.WorkoutExerciseCycleListContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutExerciseCycleListScreenHost @Inject constructor(
        override val router: Router
) : HostFragment(R.layout.fragment_container) {

    private companion object {
        const val ARG_WORKOUT_EXERCISE_ID = "workout_exercise_id"
    }

    override val children = listOf(
            R.id.container to WorkoutExerciseCycleListContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = WorkoutFeatureComponent.get().workoutExerciseCycleListComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        WorkoutFeatureComponent.get().workoutExerciseCycleListComponent.resetValue()
        return super.onBackPressed()
    }

    override fun onReceiveMessage(message: Message, sender: MessageSender) {
        when (message) {
            is Messages.ItemClickMessage -> {
                NavigationComponentStore.getDialog(Dialogs.WorkoutExerciseCycleDetailsDialog(message.id), fragmentFactory)
                        .show(childFragmentManager, null)
            }
            is Messages.KeyDataRequestMessage -> {
                sendMessage(Messages.KeyDataResponseMessage(requireArguments().getLong(ARG_WORKOUT_EXERCISE_ID)), sender as MessageReceiver)
            }
        }
    }

    fun putArguments(id: Long) {
        arguments = Bundle().apply {
            putLong(ARG_WORKOUT_EXERCISE_ID, id)
        }
    }
}