package com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_details.host

import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.component.HostFragment
import com.vladislavmyasnikov.workout_library_and_player_impl.R
import com.vladislavmyasnikov.workout_library_and_player_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creation.workout_exercise_details.content.WorkoutExerciseCycleListContent
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class WorkoutExerciseCycleListScreenHost @Inject constructor(
        override val router: Router
) : HostFragment(R.layout.fragment_container) {

    override val children = listOf(
            R.id.container to WorkoutExerciseCycleListContent::class.java
    )

    override lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        fragmentFactory = WorkoutLibraryFeatureComponent.get().workoutExerciseCycleListComponent.getValue().fragmentFactory
        super.onAttach(context)
    }

    override fun onBackPressed(): Boolean {
        WorkoutLibraryFeatureComponent.get().workoutExerciseCycleListComponent.resetValue()
        return super.onBackPressed()
    }
}