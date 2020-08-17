package com.vladislavmyasnikov.workout_diary_impl.di.module

import androidx.lifecycle.ViewModel
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.common.di.annotations.ViewModelKey
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.viewmodel.CompletedWorkoutListVM
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.viewmodel.CompletedWorkoutVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VMBindingModule {

    @Binds @IntoMap @PerScreen @ViewModelKey(CompletedWorkoutListVM::class)
    abstract fun bind1(impl: CompletedWorkoutListVM): ViewModel

    @Binds @IntoMap @PerScreen @ViewModelKey(CompletedWorkoutVM::class)
    abstract fun bind2(impl: CompletedWorkoutVM): ViewModel
}