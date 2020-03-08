package com.vladislavmyasnikov.feature_exercise_library_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.feature_exercise_library_impl.di.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.exercise.host.ExerciseHost
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.exercise_list.host.ExerciseListHost
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class ExerciseListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = ExerciseListHost::class.java
            val factory = ExerciseLibraryFeatureComponent.get().fragmentFactory
            return factory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }

    class ExerciseDetailsScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = ExerciseHost::class.java
            val factory = ExerciseLibraryFeatureComponent.get().fragmentFactory
            return factory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }
}