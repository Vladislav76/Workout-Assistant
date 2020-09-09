package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.host

import android.os.Bundle
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.communication.MessageReceiver
import com.vladislavmyasnikov.common.arch.communication.MessageSender
import com.vladislavmyasnikov.common.arch.communication.Messages
import com.vladislavmyasnikov.common.arch.component.BaseFragment
import com.vladislavmyasnikov.common.arch.component.CollapsingHeaderHostFragment
import com.vladislavmyasnikov.common.arch.navigation.RouterHolder
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutPlayerFlowLauncher
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.WorkoutFeatureComponent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.WorkoutPlayerFlow
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.content.WorkoutHeaderContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.dialog.WorkoutExerciseDetailsDialog
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutScreenHost @Inject constructor(
        override val router: Router,
        private val workoutPlayerFlowLauncher: WorkoutPlayerFlowLauncher
) : CollapsingHeaderHostFragment() {

    private companion object {
        const val ARG_WORKOUT_ID = "workout_id"
    }

    override val children: List<Pair<Int, Class<out BaseFragment>>> = listOf(
            R.id.header_container to WorkoutHeaderContent::class.java,
            R.id.body_container to WorkoutSetHost::class.java
    )

    override val fragmentFactory = WorkoutFeatureComponent.get().workoutDetailsComponent.getValue().fragmentFactory

    override fun onBackPressed(): Boolean {
        WorkoutFeatureComponent.get().workoutDetailsComponent.resetValue()
        return super.onBackPressed()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottomPanelController.hideBottomPanel()
    }

    override fun onReceiveMessage(message: Message, sender: MessageSender) {
        when (message) {
            is Messages.KeyDataRequestMessage -> {
                sendMessage(Messages.KeyDataResponseMessage(requireArguments().getLong(ARG_WORKOUT_ID)), sender as MessageReceiver)
            }
            is Messages.TransitionRequestMessage -> {
                (requireActivity() as RouterHolder).router.navigateTo(
                        workoutPlayerFlowLauncher.createScreen { (it as WorkoutPlayerFlow).putArguments(requireArguments().getLong(ARG_WORKOUT_ID)) }
                )
            }
            is Messages.ItemClickMessage -> {
                val dialogClass = WorkoutExerciseDetailsDialog::class.java

                (fragmentFactory.instantiate(dialogClass.classLoader!!, dialogClass.name) as WorkoutExerciseDetailsDialog)
                        .also {
                            it.putArguments(message.id)
                            it.show(childFragmentManager, null)
                        }
            }
        }
    }

    fun putArguments(workoutID: Long) {
        arguments = Bundle().apply {
            putLong(ARG_WORKOUT_ID, workoutID)
        }
    }
}