package com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.feature_exercise_library_impl.di.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.exercise.host.ExerciseScreenHost
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.exercise_list.host.ExerciseListScreenHost
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class ExerciseListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = ExerciseListScreenHost::class.java
            val factory = ExerciseLibraryFeatureComponent.get().fragmentFactory
            return factory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }

    class ExerciseDetailsScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = ExerciseScreenHost::class.java
            val factory = ExerciseLibraryFeatureComponent.get().fragmentFactory
            return factory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }
}