package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_details.content

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.arch_components.fundamental.VMFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.model.WorkoutSetConfig
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters.NaturalNumberAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel.WorkoutSetConfigVM
import kotlinx.android.synthetic.main.content_workout_set_details.*
import javax.inject.Inject

class WorkoutExerciseListConfigContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<WorkoutSetConfig>(R.layout.content_workout_set_details) {

    override val viewModel: WorkoutSetConfigVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutSetConfigVM::class.java)
    }

    private lateinit var workoutSetNumberAdapter: NaturalNumberAdapter
    private lateinit var workoutSetApproachAdapter: NaturalNumberAdapter

    private val workoutSetNumberClickCallback = object : AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.updateWorkoutSetNumber(id.toInt())
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    private val workoutSetApproachClickCallback = object : AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.updateWorkoutSetApproach(id.toInt())
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workoutSetNumberAdapter = NaturalNumberAdapter(requireContext(), android.R.layout.simple_spinner_item, R.string.set_label)
        workoutSetNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        workout_set_number.adapter = workoutSetNumberAdapter
        workout_set_number.onItemSelectedListener = workoutSetNumberClickCallback

        workoutSetApproachAdapter = NaturalNumberAdapter(requireContext(), android.R.layout.simple_spinner_item, R.string.approach_label)
        workoutSetApproachAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        workout_set_approach.adapter = workoutSetApproachAdapter
        workout_set_approach.onItemSelectedListener = workoutSetApproachClickCallback
    }

    override fun onReceiveItem(item: WorkoutSetConfig) {
        workoutSetNumberAdapter.lastNumber = item.setAmount
        workoutSetApproachAdapter.lastNumber = item.approachAmount
        workout_set_number.setSelection(item.setIndex)
        workout_set_approach.setSelection(item.approachIndex)
    }

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemFetchRequest) {
            viewModel.fetch()
        }
    }
}