package com.vladislavmyasnikov.feature_workout_library_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.feature_workout_library_impl.di.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.host.WorkoutHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.host.WorkoutListHost
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class WorkoutListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = WorkoutListHost::class.java
            val factory = WorkoutLibraryFeatureComponent.get().fragmentFactory
            return factory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }

    class WorkoutDetailsScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = WorkoutHost::class.java
            val factory = WorkoutLibraryFeatureComponent.get().fragmentFactory
            return factory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }
}