package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.host

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.communication.RequestMessageType
import com.vladislavmyasnikov.common.arch.communication.MessageReceiver
import com.vladislavmyasnikov.common.arch.communication.MessageSender
import com.vladislavmyasnikov.common.arch.component.HostFragment
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.ScreenStore
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.Screens
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.content.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutPlayerScreenHost @Inject constructor(
        override val router: Router
) : HostFragment(R.layout.five_fragment_container) {

    private companion object {
        const val WORKOUT_ID_ARG = "workout_id"
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
        fragmentFactory = WorkoutLibraryFeatureComponent.get().workoutPlayerComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        WorkoutLibraryFeatureComponent.get().workoutPlayerComponent.resetValue()
        return super.onBackPressed()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottomPanelController.hideBottomPanel()
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.RequestMessage) {
            when (message.type) {
                RequestMessageType.KEY_DATA_REQUEST -> sendMessage(Message.KeyDataResponseMessage(requireArguments().getLong(WORKOUT_ID_ARG)), sender as MessageReceiver)
                RequestMessageType.TRANSITION_REQUEST -> {
                    onBackPressed()
                    router.replaceScreen(ScreenStore.getScreen(Screens.WorkoutResultScreen))
                }
                else -> {}
            }
        }
    }

    fun putArguments(workoutID: Long) {
        arguments = Bundle().apply {
            putLong(WORKOUT_ID_ARG, workoutID)
        }
    }
}