package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.components.CollapsingHeaderHostFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.dialogs.WorkoutExerciseDialog
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.content.WorkoutHeaderContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutScreenHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : CollapsingHeaderHostFragment() {

    override val children = listOf(
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

    override fun onReceivePacket(packet: Packet) {
        when (packet) {
            is Packet.ItemFetchRequest -> bus.sendNoise()
            is Packet.ItemClickMessage -> {
                val dialogClass = WorkoutExerciseDialog::class.java
                val dialog = (fragmentFactory.instantiate(dialogClass.classLoader!!, dialogClass.name) as WorkoutExerciseDialog).also {
                    it.putArguments(packet.id)
                }
                dialog.show(childFragmentManager, null)
                bus.sendNoise()
            }
        }
    }
}