package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.host

import com.vladislavmyasnikov.common.experimental.Packet
import com.vladislavmyasnikov.common.experimental.SharedBus
import com.vladislavmyasnikov.common.experimental.view.HostFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.Screens
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.content.ExerciseListFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.content.ExerciseListToolbarFragment
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ExerciseLibraryFragment @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.two_fragment_container) {

    override val label = "exercise_library_fragment"

    override val children = listOf(
            R.id.top_container to ExerciseListToolbarFragment::class.java,
            R.id.bottom_container to ExerciseListFragment::class.java
    )

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemClickMessage) {
            router.navigateTo(Screens.ExerciseDetailsScreen())
            bus.sendPacket(packet)
        } else super.onReceivePacket(packet)
    }
}