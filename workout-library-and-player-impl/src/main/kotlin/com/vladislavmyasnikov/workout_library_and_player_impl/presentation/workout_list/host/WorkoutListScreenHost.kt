package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.host

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.interfaces.MessageSender
import com.vladislavmyasnikov.common.arch.fundamental.HostFragment
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.Screens
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.content.WorkoutListContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.content.WorkoutListToolbarContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutListScreenHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.two_fragment_container) {

    override val children = listOf(
            R.id.header_container to WorkoutListToolbarContent::class.java,
            R.id.body_container to WorkoutListContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = WorkoutLibraryFeatureComponent.get().workoutListComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        WorkoutLibraryFeatureComponent.get().workoutListComponent.resetValue()
        return super.onBackPressed()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottomPanelController.showBottomPanel()
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.ItemClickMessage) {
            router.navigateTo(Screens.WorkoutDetailsScreen(message.id))
        }
    }

//    override fun onReceivePacket(packet: Packet) {
//        if (packet is Packet.ItemClickMessage) {
//            router.navigateTo(Screens.WorkoutDetailsScreen())
//            bus.sendPacket(Packet.ItemFetchRequest(packet.id))
//        }
//    }
}