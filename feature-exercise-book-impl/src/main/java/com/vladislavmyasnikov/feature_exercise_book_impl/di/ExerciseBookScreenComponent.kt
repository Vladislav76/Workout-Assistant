package com.vladislavmyasnikov.feature_exercise_book_impl.di

import com.vladislavmyasnikov.core_components.di.PerScreen
import com.vladislavmyasnikov.feature_exercise_book_impl.presentation.view.ExerciseListFragment
import dagger.Subcomponent

@Subcomponent(modules = [AdapterModule::class])
@PerScreen
abstract class ExerciseBookScreenComponent {

    abstract fun inject(fragment: ExerciseListFragment)
}