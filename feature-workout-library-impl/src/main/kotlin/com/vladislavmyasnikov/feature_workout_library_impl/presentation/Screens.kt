package com.vladislavmyasnikov.feature_workout_library_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.feature_workout_library_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_details.host.CompletedWorkoutScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_list.host.CompletedWorkoutListScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.host.WorkoutScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_list.host.WorkoutListScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.host.WorkoutPlayerScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_result.host.CompletedWorkoutResultScreenHost
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    private companion object {
        val sharedFragmentFactory = WorkoutLibraryFeatureComponent.get().fragmentFactory
    }

    class WorkoutListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = WorkoutListScreenHost::class.java
            return sharedFragmentFactory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }

    class WorkoutDetailsScreen(private val workoutID: Long) : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = WorkoutScreenHost::class.java
            return sharedFragmentFactory.instantiate(clazz.classLoader!!, clazz.name).also { createdFragment ->
                (createdFragment as WorkoutScreenHost).putArguments(workoutID)
            }
        }
    }

    class WorkoutPlayerScreen(private val workoutID: Long) : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = WorkoutPlayerScreenHost::class.java
            return sharedFragmentFactory.instantiate(clazz.classLoader!!, clazz.name).also { createdFragment ->
                (createdFragment as WorkoutPlayerScreenHost).putArguments(workoutID)
            }
        }
    }

    class WorkoutResultScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = CompletedWorkoutResultScreenHost::class.java
            return sharedFragmentFactory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }

    class CompletedWorkoutListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = CompletedWorkoutListScreenHost::class.java
            return sharedFragmentFactory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }

    class CompletedWorkoutDetailsScreen(private val id: Long) : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = CompletedWorkoutScreenHost::class.java
            return sharedFragmentFactory.instantiate(clazz.classLoader!!, clazz.name)
                    .also { (it as CompletedWorkoutScreenHost).putArguments(id) }
        }
    }
}