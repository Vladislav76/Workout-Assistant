package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.content

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.vladislavmyasnikov.common.experimental.Packet
import com.vladislavmyasnikov.common.experimental.SharedBus
import com.vladislavmyasnikov.common.experimental.view.VMFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.FullExerciseInfo
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.adapters.ExerciseImagePagerAdapter
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ExerciseViewModel
import kotlinx.android.synthetic.main.fragment_exercise_details.*
import javax.inject.Inject

class ExerciseFragment @Inject constructor(
        override val bus: SharedBus,
        private val adapter: ExerciseImagePagerAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<FullExerciseInfo>(R.layout.fragment_exercise_details) {

    override val label = "exercise_fragment"

    override lateinit var viewModel: ExerciseViewModel

    private lateinit var muscleGroupNames: Array<String>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ExerciseViewModel::class.java)
        muscleGroupNames = context.resources.getStringArray(R.array.muscle_groups)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = adapter
    }

    override fun onReceiveItem(item: FullExerciseInfo) {
        title_field.text = item.title
        description_field.text = item.description
        adapter.imagesIDs = item.imagesIDs

        for ((index, muscleGroupID) in item.muscleGroupsIDs.withIndex()) {
            val tag = Chip(context).apply {
                id = index
                text = muscleGroupNames[muscleGroupID]
            }
            muscle_groups_tags.addView(tag)
        }
    }

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemClickMessage) {
            viewModel.fetch(packet.id)
        } else super.onReceivePacket(packet)
    }
}