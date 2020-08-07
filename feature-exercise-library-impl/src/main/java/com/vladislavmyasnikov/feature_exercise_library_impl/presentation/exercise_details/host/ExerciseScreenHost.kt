package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.exercise_details.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.Message
import com.vladislavmyasnikov.common.arch.SharedBus
import com.vladislavmyasnikov.common.arch.fundamental.HostFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.R
import com.vladislavmyasnikov.feature_exercise_library_impl.di.component.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.exercise_details.content.ExerciseContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ExerciseScreenHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.fragment_container) {

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

    override fun onReceivePacket(message: Message) {
        if (message is Message.KeyDataResponseMessage) {
            bus.sendNoise()
        }
    }
}