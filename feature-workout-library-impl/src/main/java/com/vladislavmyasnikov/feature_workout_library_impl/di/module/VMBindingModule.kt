package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import androidx.lifecycle.ViewModel
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.common.di.annotations.ViewModelKey
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VMBindingModule {

    @Binds @IntoMap @PerScreen @ViewModelKey(WorkoutListVM::class)
    abstract fun bind1(impl: WorkoutListVM): ViewModel

    @Binds @IntoMap @PerScreen @ViewModelKey(WorkoutVM::class)
    abstract fun bind2(impl: WorkoutVM): ViewModel

    @Binds @IntoMap @PerScreen @ViewModelKey(WorkoutExerciseVM::class)
    abstract fun bind3(impl: WorkoutExerciseVM): ViewModel

    @Binds @IntoMap @PerScreen @ViewModelKey(WorkoutExerciseListVM::class)
    abstract fun bind4(impl: WorkoutExerciseListVM): ViewModel

    @Binds @IntoMap @PerScreen @ViewModelKey(WorkoutSetConfigVM::class)
    abstract fun bind5(impl: WorkoutSetConfigVM): ViewModel

    @Binds @IntoMap @PerScreen @ViewModelKey(WorkoutPlayerVM::class)
    abstract fun bind6(impl: WorkoutPlayerVM): ViewModel

    @Binds @IntoMap @PerScreen @ViewModelKey(WorkoutExerciseConfigVM::class)
    abstract fun bind7(impl: WorkoutExerciseConfigVM): ViewModel

    @Binds @IntoMap @PerScreen @ViewModelKey(ExerciseApproachDataVM::class)
    abstract fun bind8(impl: ExerciseApproachDataVM): ViewModel
}