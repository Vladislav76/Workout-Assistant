package com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.component.HostFragment
import com.vladislavmyasnikov.common.arch.communication.MessageSender
import com.vladislavmyasnikov.workout_diary_impl.R
import com.vladislavmyasnikov.workout_diary_impl.di.component.DiaryFeatureComponent
import com.vladislavmyasnikov.workout_diary_impl.presentation.Screens
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.content.CompletedWorkoutListContent
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.content.CompletedWorkoutListToolbarContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class CompletedWorkoutListScreenHost @Inject constructor(
        override val router: Router
) : HostFragment(R.layout.two_fragment_container) {

    override val children = listOf(
            R.id.header_container to CompletedWorkoutListToolbarContent::class.java,
            R.id.body_container to CompletedWorkoutListContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = DiaryFeatureComponent.get().diaryEntryListComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        DiaryFeatureComponent.get().diaryEntryListComponent.resetValue()
        return super.onBackPressed()
    }

    override fun onReceiveMessage(message: Message, sender: MessageSender) {
        if (message is Message.ItemClickMessage) { router.navigateTo(Screens.DiaryEntryDetailsScreen(message.id)) }
    }
}