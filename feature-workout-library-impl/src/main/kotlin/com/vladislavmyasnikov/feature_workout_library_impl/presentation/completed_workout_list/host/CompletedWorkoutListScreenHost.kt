package com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_list.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.HostFragment
import com.vladislavmyasnikov.common.interfaces.MessageSender
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.Screens
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_list.content.CompletedWorkoutListContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_list.content.CompletedWorkoutListToolbarContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CompletedWorkoutListScreenHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.two_fragment_container) {

    override val children = listOf(
            R.id.header_container to CompletedWorkoutListToolbarContent::class.java,
            R.id.body_container to CompletedWorkoutListContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = WorkoutLibraryFeatureComponent.get().completedWorkoutListComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        if (super.onBackPressed()) WorkoutLibraryFeatureComponent.get().completedWorkoutListComponent.resetValue()
        return true
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.ItemClickMessage) {
            router.navigateTo(Screens.CompletedWorkoutDetailsScreen(message.id))
        }
    }
}