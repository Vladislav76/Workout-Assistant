package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.component.HostFragment
import com.vladislavmyasnikov.common.arch.communication.MessageSender
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.content.WorkoutExerciseListConfigContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.content.WorkoutExerciseListContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutSetHost @Inject constructor(
        override val router: Router
) : HostFragment(R.layout.two_fragment_container) {

    override val children = listOf(
            R.id.header_container to WorkoutExerciseListConfigContent::class.java,
            R.id.body_container to WorkoutExerciseListContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = parentFragmentManager.fragmentFactory
        super.onAttach(context)
    }

    override fun onReceiveMessage(message: Message, sender: MessageSender) {
        relayMessage(message, sender)
    }
}