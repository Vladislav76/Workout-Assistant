package com.vladislavmyasnikov.feature_workout_library_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.feature_workout_library_impl.di.component.WorkoutLibraryFeatureComponent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.host.WorkoutScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_list.host.WorkoutListScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.host.WorkoutPlayerScreenHost
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class WorkoutListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = WorkoutListScreenHost::class.java
            val factory = WorkoutLibraryFeatureComponent.get().fragmentFactory
            return factory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }

    class WorkoutDetailsScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = WorkoutScreenHost::class.java
            val factory = WorkoutLibraryFeatureComponent.get().fragmentFactory
            return factory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }

    class WorkoutPlayerScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = WorkoutPlayerScreenHost::class.java
            val factory = WorkoutLibraryFeatureComponent.get().fragmentFactory
            return factory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }
}