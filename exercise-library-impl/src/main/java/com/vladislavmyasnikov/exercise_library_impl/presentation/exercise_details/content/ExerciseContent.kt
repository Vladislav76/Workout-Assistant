package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_details.content

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.communication.RequestMessageType
import com.vladislavmyasnikov.common.arch.component.VMFragment
import com.vladislavmyasnikov.common.extensions.injectViewModel
import com.vladislavmyasnikov.common.arch.communication.MessageSender
import com.vladislavmyasnikov.exercise_library_impl.R
import com.vladislavmyasnikov.exercise_library_impl.domain.entity.Exercise
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_details.adapter.ExerciseImagePagerAdapter
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_details.viewmodel.ExerciseVM
import kotlinx.android.synthetic.main.content_exercise_details.*
import javax.inject.Inject

class ExerciseContent @Inject constructor(
        private val adapter: ExerciseImagePagerAdapter,
        override val viewModelFactory: ViewModelProvider.Factory
) : VMFragment<Exercise>(R.layout.content_exercise_details) {

    override val viewModel by lazy { injectViewModel<ExerciseVM>(viewModelFactory) }

    private val muscleGroupNames: Array<String> by lazy { requireContext().resources.getStringArray(R.array.muscle_groups) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = adapter
        sendMessage(Message.RequestMessage(RequestMessageType.KEY_DATA_REQUEST))
    }

    override fun onReceiveItem(item: Exercise) {
        title_field.text = item.title
        description_field.text = item.description
        adapter.imagesIDs = item.imagesIDs

        val muscleGroups = item.muscleGroupsIDs.map { muscleGroupNames[it] }
        muscle_groups_tags.setItems(muscleGroups)
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.KeyDataResponseMessage) { viewModel.request(message.id) }
    }
}