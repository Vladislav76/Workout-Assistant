package com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.presentation.view.HostFragment
import com.vladislavmyasnikov.feature_workout_library_impl.R
import com.vladislavmyasnikov.feature_workout_library_impl.di.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.content.WorkoutContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.content.WorkoutSetListContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutHost @Inject constructor(
        override val bus: SharedBus,
        override val router: Router
) : HostFragment(R.layout.two_fragment_container) {

    override val label = "WORKOUT_HF"

    override val children = listOf(
            R.id.top_container to WorkoutContent::class.java,
            R.id.bottom_container to WorkoutSetListContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = WorkoutLibraryFeatureComponent.get().workoutDetailsComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        WorkoutLibraryFeatureComponent.get().workoutDetailsComponent.resetValue()
        return super.onBackPressed()
    }
}