package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.host

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.RequestMessageType
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.interfaces.MessageReceiver
import com.vladislavmyasnikov.common.interfaces.MessageSender
import com.vladislavmyasnikov.common.arch.fundamental.HostFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.content.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutPlayerScreenHost @Inject constructor(
        override val bus: SharedBus,
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

//    override fun onReceivePacket(message: Message) {
//        if (message is Message.ItemFetchRequest) {
//            bus.sendNoise()
//        }
//    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.RequestMessage && message.type == RequestMessageType.KEY_DATA_REQUEST) {
            sendMessage(Message.KeyDataResponseMessage(requireArguments().getLong(WORKOUT_ID_ARG)), sender as MessageReceiver)
        }
    }

    fun putArguments(workoutID: Long) {
        arguments = Bundle().apply {
            putLong(WORKOUT_ID_ARG, workoutID)
        }
    }
}