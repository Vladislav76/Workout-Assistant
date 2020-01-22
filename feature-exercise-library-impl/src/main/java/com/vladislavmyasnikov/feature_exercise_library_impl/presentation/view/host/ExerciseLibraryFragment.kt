package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.HostFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.di.FeatureComponent
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

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = FeatureComponent.get().exerciseLibraryComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemClickMessage) {
            router.navigateTo(Screens.ExerciseDetailsScreen())
        } else super.onReceivePacket(packet)
    }

    override fun onBackPressed(): Boolean {
        FeatureComponent.get().exerciseLibraryComponent.resetValue()
        return super.onBackPressed()
    }
}