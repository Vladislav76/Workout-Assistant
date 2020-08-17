package com.vladislavmyasnikov.workout_diary_impl.presentation

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.workout_diary_impl.di.component.DiaryFeatureComponent
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.host.CompletedWorkoutScreenHost
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.host.CompletedWorkoutListScreenHost
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    private companion object {
        val sharedFragmentFactory = DiaryFeatureComponent.get().fragmentFactory
    }

    class DiaryEntryListScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = CompletedWorkoutListScreenHost::class.java
            return sharedFragmentFactory.instantiate(clazz.classLoader!!, clazz.name)
        }
    }

    class DiaryEntryDetailsScreen(private val diaryEntryId: Long) : SupportAppScreen() {

        override fun getFragment(): Fragment {
            val clazz = CompletedWorkoutScreenHost::class.java
            return sharedFragmentFactory.instantiate(clazz.classLoader!!, clazz.name)
                    .also { fragment -> (fragment as CompletedWorkoutScreenHost).putArguments(diaryEntryId) }
        }
    }
}