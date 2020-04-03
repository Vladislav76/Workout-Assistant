package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_player.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.arch_components.fundamental.HostFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.Screens
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_player.content.ControlPanelContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_player.content.WorkoutExerciseConfigContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_player.content.WorkoutExerciseContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutPlayerScreenHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.three_fragment_container) {

    override val label = "WORKOUT_PLAYER_HF"

    override val children = listOf(
            R.id.top_container to WorkoutExerciseContent::class.java,
            R.id.middle_container to WorkoutExerciseConfigContent::class.java,
            R.id.bottom_container to ControlPanelContent::class.java
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

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemFetchRequest) {
            bus.sendNoise()
        }
    }
}