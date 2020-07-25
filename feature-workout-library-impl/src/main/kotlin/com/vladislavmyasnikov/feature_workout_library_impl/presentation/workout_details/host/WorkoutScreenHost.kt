package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.host

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.RequestMessageType
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.interfaces.MessageReceiver
import com.vladislavmyasnikov.common.interfaces.MessageSender
import com.vladislavmyasnikov.common.arch.fundamental.BaseFragment
import com.vladislavmyasnikov.common.presentation.view.components.CollapsingHeaderHostFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.Screens
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.content.WorkoutHeaderContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutScreenHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : CollapsingHeaderHostFragment() {

    private companion object {
        const val WORKOUT_ID_ARG = "workout_id"
    }

    override val children: List<Pair<Int, Class<out BaseFragment>>> = listOf(
            R.id.header_container to WorkoutHeaderContent::class.java,
            R.id.body_container to WorkoutSetHost::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = WorkoutLibraryFeatureComponent.get().workoutDetailsComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        WorkoutLibraryFeatureComponent.get().workoutDetailsComponent.resetValue()
        return super.onBackPressed()
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        when (message) {
            is Message.RequestMessage -> {
                when (message.type) {
                    RequestMessageType.KEY_DATA_REQUEST -> {
                        if (sender is MessageReceiver) {
                            sendMessage(Message.KeyDataResponseMessage(requireArguments().getLong(WORKOUT_ID_ARG)), sender)
                        }
                    }
                    RequestMessageType.TRANSITION_REQUEST -> {
                        router.navigateTo(Screens.WorkoutPlayerScreen(requireArguments().getLong(WORKOUT_ID_ARG)))
                    }
                }
            }
        }
    }

//    override fun onReceivePacket(packet: Packet) {
//        when (packet) {
//            is Packet.ItemFetchRequest -> bus.sendNoise()
//            is Packet.ItemClickMessage -> {
//                val dialogClass = WorkoutExerciseDialog::class.java
//                val dialog = (fragmentFactory.instantiate(dialogClass.classLoader!!, dialogClass.name) as WorkoutExerciseDialog).also {
//                    it.putArguments(packet.id)
//                }
//                dialog.show(childFragmentManager, null)
//                bus.sendNoise()
//            }
//            is Packet.StartWorkoutMessage -> {
//                router.navigateTo(Screens.WorkoutPlayerScreen())
//                bus.sendPacket(Packet.ItemFetchRequest(packet.id))
//            }
//        }
//    }

    fun putArguments(workoutID: Long) {
        arguments = Bundle().apply {
            putLong(WORKOUT_ID_ARG, workoutID)
        }
    }
}