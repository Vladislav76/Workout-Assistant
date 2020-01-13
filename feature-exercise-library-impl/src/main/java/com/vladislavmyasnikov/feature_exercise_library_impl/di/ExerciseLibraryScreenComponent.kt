package com.vladislavmyasnikov.feature_exercise_library_impl.di

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.view.content.ExerciseListFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.view.content.ExerciseListToolbarFragment
import dagger.Subcomponent

@Subcomponent(modules = []) // TODO: change to component
@PerScreen
abstract class ExerciseLibraryScreenComponent {

    abstract fun inject(fragment: ExerciseListFragment)
    abstract fun inject(fragment: ExerciseListToolbarFragment)
}