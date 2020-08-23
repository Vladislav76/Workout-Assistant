package com.vladislavmyasnikov.exercise_library_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.exercise_library_impl.di.component.ExerciseLibraryFeatureComponent
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_details.host.ExerciseScreenHost
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.host.ExerciseListScreenHost
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    private companion object {
        val sharedFragmentFactory = ExerciseLibraryFeatureComponent.get().fragmentFactory
    }

    class ExerciseListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = ExerciseListScreenHost::class.java
            return sharedFragmentFactory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }

    class ExerciseDetailsScreen(private val id: Long) : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = ExerciseScreenHost::class.java
            return sharedFragmentFactory.instantiate(clazz.classLoader!!, clazz.name)
                    .also { (it as ExerciseScreenHost).putArguments(id) }
        }
    }
}