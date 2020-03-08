package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.exercise.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.arch_components.fundamental.HostFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.di.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.exercise.content.ExerciseContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ExerciseHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.fragment_container) {

    override val label = "EXERCISE_HF"

    override val children = listOf(
            R.id.container to ExerciseContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = ExerciseLibraryFeatureComponent.get().exerciseDetailsComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        ExerciseLibraryFeatureComponent.get().exerciseDetailsComponent.resetValue()
        return super.onBackPressed()
    }

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemFetchRequest) {
            bus.sendNoise()
        }
    }
}