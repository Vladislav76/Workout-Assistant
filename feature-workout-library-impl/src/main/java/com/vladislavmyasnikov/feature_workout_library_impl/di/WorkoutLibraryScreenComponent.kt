package com.vladislavmyasnikov.feature_workout_library_impl.di

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.WorkoutFragment
import dagger.Subcomponent

@Subcomponent(modules = [AdapterModule::class])
@PerScreen
abstract class WorkoutLibraryScreenComponent {

    abstract fun inject(fragment: WorkoutFragment)
}