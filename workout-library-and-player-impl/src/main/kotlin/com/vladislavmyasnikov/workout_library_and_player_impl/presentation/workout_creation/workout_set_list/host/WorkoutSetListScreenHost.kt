package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_set_list.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.communication.MessageSender
import com.vladislavmyasnikov.common.arch.component.HostFragment
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.ScreenStore
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.Screens
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_set_list.content.WorkoutSetListContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutSetListScreenHost @Inject constructor(
        override val router: Router
) : HostFragment(R.layout.fragment_container) {

    override val children = listOf(
            R.id.container to WorkoutSetListContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = WorkoutLibraryFeatureComponent.get().workoutSetListComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        WorkoutLibraryFeatureComponent.get().workoutSetListComponent.resetValue()
        return super.onBackPressed()
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.ItemClickMessage) {
            // TODO: send message.id to new screen
            router.navigateTo(ScreenStore.getScreen(Screens.WorkoutExerciseListScreen))
        }
    }
}