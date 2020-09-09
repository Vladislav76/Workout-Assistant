package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_list.host

import android.os.Bundle
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.communication.MessageSender
import com.vladislavmyasnikov.common.arch.communication.Messages
import com.vladislavmyasnikov.common.arch.component.HostFragment
import com.vladislavmyasnikov.common.arch.navigation.RouterHolder
import com.vladislavmyasnikov.workout_library_and_player_api.WorkoutCreatorFlowLauncher
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.WorkoutFeatureComponent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.NavigationComponentStore
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.Screens
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_list.content.WorkoutListContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_list.content.WorkoutListToolbarContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutListScreenHost @Inject constructor(
        override val router: Router,
        private val workoutCreatorFlowLauncher: WorkoutCreatorFlowLauncher
) : HostFragment(R.layout.two_fragment_container) {

    override val children = listOf(
            R.id.header_container to WorkoutListToolbarContent::class.java,
            R.id.body_container to WorkoutListContent::class.java
    )

    override val fragmentFactory = WorkoutFeatureComponent.get().workoutListComponent.getValue().fragmentFactory

    override fun onBackPressed(): Boolean {
        WorkoutFeatureComponent.get().workoutListComponent.resetValue()
        return super.onBackPressed()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottomPanelController.showBottomPanel()
    }

    override fun onReceiveMessage(message: Message, sender: MessageSender) {
        when (message) {
            is Messages.ItemClickMessage -> {
                router.navigateTo(NavigationComponentStore.getScreen(Screens.WorkoutDetailsScreen(message.id)))
            }
            Messages.TransitionRequestMessage -> {
                (requireActivity() as RouterHolder).router.navigateTo(
                        workoutCreatorFlowLauncher.createScreen()
                )
            }
        }
    }
}