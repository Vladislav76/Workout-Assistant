package com.vladislavmyasnikov.feature_workout_library_impl.di

import androidx.lifecycle.ViewModel
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.common.di.annotations.ViewModelKey
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels.WorkoutListVM
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodels.WorkoutVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VMBindingModule {

    @Binds @IntoMap @PerScreen @ViewModelKey(WorkoutListVM::class)
    abstract fun bind1(impl: WorkoutListVM): ViewModel

    @Binds @IntoMap @PerScreen @ViewModelKey(WorkoutVM::class)
    abstract fun bind2(impl: WorkoutVM): ViewModel
}