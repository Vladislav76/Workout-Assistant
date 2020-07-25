package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.exercise.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.VMFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.domain.model.FullExercise
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.adapters.ExerciseImagePagerAdapter
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodel.ExerciseVM
import kotlinx.android.synthetic.main.content_exercise_details.*
import javax.inject.Inject

class ExerciseContent @Inject constructor(
        override val bus: SharedBus,
        private val adapter: ExerciseImagePagerAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<FullExercise>(R.layout.content_exercise_details) {

    override val viewModel: ExerciseVM by lazy {
        ViewModelProvider(this, viewModelFactory).get(ExerciseVM::class.java)
    }

    private val muscleGroupNames: Array<String> by lazy {
        context!!.resources.getStringArray(R.array.muscle_groups)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = adapter
    }

    override fun onReceiveItem(item: FullExercise) {
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
        debugMessage("::onReceiveItem")
    }

    override fun onReceivePacket(message: Message) {
        if (message is Message.KeyDataResponseMessage) {
            viewModel.fetch(message.id)
        }
    }
}