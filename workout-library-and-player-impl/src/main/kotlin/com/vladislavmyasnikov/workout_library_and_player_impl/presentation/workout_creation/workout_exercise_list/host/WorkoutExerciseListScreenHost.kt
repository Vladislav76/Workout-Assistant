package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_list.host

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
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.NavigationComponentStore
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.Screens
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_list.content.WorkoutExerciseListContent2
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutExerciseListScreenHost @Inject constructor(
        override val router: Router
) : HostFragment(R.layout.fragment_container) {

    private companion object {
        const val ARG_WORKOUT_SET_ID = "workout_set_id"
    }

    override val children = listOf(
            R.id.container to WorkoutExerciseListContent2::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = WorkoutFeatureComponent.get().workoutExerciseListComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        WorkoutFeatureComponent.get().workoutExerciseListComponent.resetValue()
        return super.onBackPressed()
    }

    override fun onReceiveMessage(message: Message, sender: MessageSender) {
        when (message) {
            is Messages.ItemClickMessage -> {
                router.navigateTo(NavigationComponentStore.getScreen(Screens.WorkoutExerciseCycleListScreen(message.id)))
            }
            is Messages.KeyDataRequestMessage -> {
                sendMessage(Messages.KeyDataResponseMessage(requireArguments().getLong(ARG_WORKOUT_SET_ID)), sender as MessageReceiver)
            }
        }
    }

    fun putArguments(id: Long) {
        arguments = Bundle().apply {
            putLong(ARG_WORKOUT_SET_ID, id)
        }
    }
}