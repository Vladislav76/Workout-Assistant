package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_execution.workout_player.host

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
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_execution.workout_player.content.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutPlayerScreenHost @Inject constructor(
        override val router: Router
) : HostFragment(R.layout.five_fragment_container) {

    private companion object {
        const val ARG_WORKOUT_ID = "workout_id"
    }

    override val children = listOf(
            R.id.container_1 to WorkoutExerciseContent::class.java,
            R.id.container_2 to WorkoutExerciseConfigContent::class.java,
            R.id.container_3 to WorkoutExerciseMetricsContent::class.java,
            R.id.container_4 to TimerContent::class.java,
            R.id.container_5 to ControlPanelContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = WorkoutFeatureComponent.get().workoutPlayerComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        WorkoutFeatureComponent.get().workoutPlayerComponent.resetValue()
        return super.onBackPressed()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottomPanelController.hideBottomPanel()
    }

    override fun onReceiveMessage(message: Message, sender: MessageSender) {
        when (message) {
            Messages.KeyDataRequestMessage -> {
                sendMessage(Messages.KeyDataResponseMessage(requireArguments().getLong(ARG_WORKOUT_ID)), sender as MessageReceiver)
            }
            Messages.TransitionRequestMessage -> {
                onBackPressed()
                router.replaceScreen(NavigationComponentStore.getScreen(Screens.WorkoutResultScreen))
            }
        }
    }

    fun putArguments(workoutID: Long) {
        arguments = Bundle().apply {
            putLong(ARG_WORKOUT_ID, workoutID)
        }
    }
}