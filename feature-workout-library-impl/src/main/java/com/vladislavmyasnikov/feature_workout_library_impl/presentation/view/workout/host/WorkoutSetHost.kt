package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.HostFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.content.WorkoutExerciseListContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.content.WorkoutSetContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutSetHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.two_fragment_container) {

    override val label = "WORKOUT_SET_HF"

    override val children = listOf(
            R.id.header_container to WorkoutSetContent::class.java,
            R.id.body_container to WorkoutExerciseListContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = parentFragmentManager.fragmentFactory
        super.onAttach(context)
    }
}