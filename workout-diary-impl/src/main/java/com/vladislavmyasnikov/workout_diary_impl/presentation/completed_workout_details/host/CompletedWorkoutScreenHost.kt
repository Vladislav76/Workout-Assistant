package com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.host

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.RequestMessageType
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.HostFragment
import com.vladislavmyasnikov.common.interfaces.MessageReceiver
import com.vladislavmyasnikov.common.interfaces.MessageSender
import com.vladislavmyasnikov.workout_diary_impl.R
import com.vladislavmyasnikov.workout_diary_impl.di.component.DiaryFeatureComponent
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.content.CompletedWorkoutContent
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.content.CompletedWorkoutToolbarContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CompletedWorkoutScreenHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.two_fragment_container) {

    private companion object {
        const val ARG_DIARY_ENTRY_ID = "diary_entry_id"
    }

    override val children = listOf(
            R.id.header_container to CompletedWorkoutToolbarContent::class.java,
            R.id.body_container to CompletedWorkoutContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = DiaryFeatureComponent.get().diaryEntryComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        DiaryFeatureComponent.get().diaryEntryComponent.resetValue()
        return super.onBackPressed()
    }

    override fun receiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.RequestMessage && message.type == RequestMessageType.KEY_DATA_REQUEST) {
            sendMessage(Message.KeyDataResponseMessage(requireArguments().getLong(ARG_DIARY_ENTRY_ID)), sender as MessageReceiver)
        }
    }

    fun putArguments(diaryEntryId: Long) {
        arguments = Bundle().apply {
            putLong(ARG_DIARY_ENTRY_ID, diaryEntryId)
        }
    }
}