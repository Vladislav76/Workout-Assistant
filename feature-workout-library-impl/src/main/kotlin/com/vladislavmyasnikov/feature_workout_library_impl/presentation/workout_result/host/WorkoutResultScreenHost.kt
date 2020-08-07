package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_result.host

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.RequestMessageType
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.HostFragment
import com.vladislavmyasnikov.common.interfaces.MessageSender
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.Screens
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_result.content.WorkoutResultContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutResultScreenHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.fragment_container) {

    override val children = listOf(
            R.id.container to WorkoutResultContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = WorkoutLibraryFeatureComponent.get().workoutResultComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        WorkoutLibraryFeatureComponent.get().workoutResultComponent.resetValue()
        return super.onBackPressed()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bottomPanelController.hideBottomPanel()
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.RequestMessage && message.type == RequestMessageType.TRANSITION_REQUEST) {
            onBackPressed()
        }
    }
}