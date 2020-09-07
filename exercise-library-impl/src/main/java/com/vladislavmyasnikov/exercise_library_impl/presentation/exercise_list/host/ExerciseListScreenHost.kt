package com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.communication.MessageSender
import com.vladislavmyasnikov.common.arch.communication.Messages
import com.vladislavmyasnikov.common.arch.component.HostFragment
import com.vladislavmyasnikov.exercise_library_impl.R
import com.vladislavmyasnikov.exercise_library_impl.di.component.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.exercise_library_impl.presentation.Screens
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.content.ExerciseListContent
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.content.ExerciseListToolbarContent
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.dialog.ExerciseFilterDialog
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ExerciseListScreenHost @Inject constructor(
        override val router: Router
) : HostFragment(R.layout.two_fragment_container) {

    override val children = listOf(
            R.id.header_container to ExerciseListToolbarContent::class.java,
            R.id.body_container to ExerciseListContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = ExerciseLibraryFeatureComponent.get().exerciseListComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        ExerciseLibraryFeatureComponent.get().exerciseListComponent.resetValue()
        return super.onBackPressed()
    }

    override fun onReceiveMessage(message: Message, sender: MessageSender) {
        when (message) {
            is Messages.ItemClickMessage -> {
                router.navigateTo(Screens.ExerciseDetailsScreen(message.id))
            }
            is Messages.TransitionRequestMessage -> {
                val clazz = ExerciseFilterDialog::class.java
                fragmentFactory.instantiate(clazz.classLoader!!, clazz.name)
                        .also { (it as ExerciseFilterDialog).show(childFragmentManager, null) }
            }
        }
    }
}