package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.content

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.VMFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.domain.WorkoutExercise
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters.NaturalNumberAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels.WorkoutExerciseListVM
import kotlinx.android.synthetic.main.content_workout_set_details.*
import javax.inject.Inject

class WorkoutSetContent @Inject constructor(
        override val bus: SharedBus,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<List<WorkoutExercise>>(R.layout.content_workout_set_details) {

    override val label = "WORKOUT_SET_CF"

    override val viewModel: WorkoutExerciseListVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(WorkoutExerciseListVM::class.java)
    }

    private lateinit var workoutSetNumberAdapter: NaturalNumberAdapter
    private lateinit var workoutSetApproachAdapter: NaturalNumberAdapter

    private val workoutSetNumberClickCallback = object : AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.workoutSetNumber = id.toInt()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    private val workoutSetApproachClickCallback = object : AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            viewModel.workoutSetApproach = id.toInt()
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workoutSetNumberAdapter = NaturalNumberAdapter(requireContext(), android.R.layout.simple_spinner_item, R.string.workout_set_number_label)
        workoutSetNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        workout_set_number.adapter = workoutSetNumberAdapter
        workout_set_number.onItemSelectedListener = workoutSetNumberClickCallback

        workoutSetApproachAdapter = NaturalNumberAdapter(requireContext(), android.R.layout.simple_spinner_item, R.string.workout_set_approach_label)
        workoutSetApproachAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        workout_set_approach.adapter = workoutSetApproachAdapter
        workout_set_approach.onItemSelectedListener = workoutSetApproachClickCallback
    }

    override fun onReceiveItem(item: List<WorkoutExercise>) {
        workoutSetNumberAdapter.lastNumber = viewModel.workoutSetAmount
        workoutSetApproachAdapter.lastNumber = viewModel.workoutSetApproachAmount
        workout_set_number.setSelection(viewModel.workoutSetNumber)
        workout_set_approach.setSelection(viewModel.workoutSetApproach)
    }

    override fun onReceivePacket(packet: Packet) {
        if (packet is Packet.ItemClickMessage) {
            viewModel.fetch(packet.id)
            bus.sendPacket(Packet.EmptyMessage())
        } else super.onReceivePacket(packet)
    }
}