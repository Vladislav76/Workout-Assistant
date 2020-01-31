package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.HostFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.di.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.Screens
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.content.WorkoutListContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.content.WorkoutListToolbarContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutListHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.two_fragment_container) {

    override val label = "WORKOUT_LIST_HF"

    override val children = listOf(
            R.id.top_container to WorkoutListToolbarContent::class.java,
            R.id.bottom_container to WorkoutListContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = WorkoutLibraryFeatureComponent.get().workoutListComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemClickMessage) {
            router.navigateTo(Screens.WorkoutDetailsScreen())
        } else super.onReceivePacket(packet)
    }

    override fun onBackPressed(): Boolean {
        WorkoutLibraryFeatureComponent.get().workoutListComponent.resetValue()
        return super.onBackPressed()
    }
}