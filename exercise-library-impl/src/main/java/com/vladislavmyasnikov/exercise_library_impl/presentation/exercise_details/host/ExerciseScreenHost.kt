package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_details.host

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.communication.RequestMessageType
import com.vladislavmyasnikov.common.arch.component.HostFragment
import com.vladislavmyasnikov.common.arch.communication.MessageReceiver
import com.vladislavmyasnikov.common.arch.communication.MessageSender
import com.vladislavmyasnikov.exercise_library_impl.R
import com.vladislavmyasnikov.exercise_library_impl.di.component.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_details.content.ExerciseContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ExerciseScreenHost @Inject constructor(
        override val router: Router
) : HostFragment(R.layout.fragment_container) {

    private companion object {
        const val ARG_EXERCISE_ID = "exercise_id"
    }

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

    override fun receiveMessage(message: Message, sender: MessageSender) {
        when (message) {
            is Message.RequestMessage -> {
                when (message.type) {
                    RequestMessageType.KEY_DATA_REQUEST -> {
                        sendMessage(Message.KeyDataResponseMessage(requireArguments().getLong(ARG_EXERCISE_ID)), sender as MessageReceiver)
                    }
                }
            }
        }
    }

    fun putArguments(exerciseId: Long) {
        arguments = Bundle().apply {
            putLong(ARG_EXERCISE_ID, exerciseId)
        }
    }
}