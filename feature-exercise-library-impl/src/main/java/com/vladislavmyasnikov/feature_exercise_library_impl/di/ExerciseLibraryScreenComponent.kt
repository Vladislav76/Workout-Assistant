package com.vladislavmyasnikov.feature_exercise_library_impl.di

import com.vladislavmyasnikov.common.di.PerScreen
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.content.ExerciseListToolbarFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.ExerciseFragment
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.ExerciseListFragment
import dagger.Subcomponent

@Subcomponent(modules = [CallbackModule::class, AdapterModule::class]) // TODO: change to component
@PerScreen
abstract class ExerciseLibraryScreenComponent {

    abstract fun inject(fragment: ExerciseFragment)
    abstract fun inject(fragment: ExerciseListFragment)
    abstract fun inject(fragment: com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.content.ExerciseListFragment)
    abstract fun inject(fragment: ExerciseListToolbarFragment)
}