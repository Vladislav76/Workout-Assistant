package com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.content

import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.components.VMHeaderFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.FullWorkout
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.viewmodel.WorkoutVM
import kotlinx.android.synthetic.main.content_workout_banner.*
import javax.inject.Inject

class WorkoutHeaderContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMHeaderFragment<FullWorkout>(R.layout.content_workout_banner) {

    override val viewModel: WorkoutVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutVM::class.java)
    }

    override fun onReceiveItem(item: FullWorkout) {
        val context = requireContext()

        setTitle(item.title)

        val resID = context.resources.getIdentifier(item.avatarID, "drawable", context.packageName)
        workout_avatar.setImageDrawable(ContextCompat.getDrawable(context, resID))
    }

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemFetchRequest) {
            viewModel.fetch(packet.id)
        }
    }
}