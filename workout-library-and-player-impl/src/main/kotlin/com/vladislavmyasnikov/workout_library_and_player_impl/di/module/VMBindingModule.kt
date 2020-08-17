package com.vladislavmyasnikov.workout_library_and_player_impl.di.module

import androidx.lifecycle.ViewModel
import com.vladislavmyasnikov.common.di.annotations.ViewModelKey
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.viewmodel.WorkoutExerciseListVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.viewmodel.WorkoutSetConfigVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.viewmodel.WorkoutVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.viewmodel.WorkoutListVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.viewmodel.*
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_result.viewmodel.WorkoutResultVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VMBindingModule {

    @Binds @IntoMap @ViewModelKey(WorkoutListVM::class)
    abstract fun bind1(impl: WorkoutListVM): ViewModel

    @Binds @IntoMap @ViewModelKey(WorkoutVM::class)
    abstract fun bind2(impl: WorkoutVM): ViewModel

    @Binds @IntoMap @ViewModelKey(WorkoutExerciseVM::class)
    abstract fun bind3(impl: WorkoutExerciseVM): ViewModel

    @Binds @IntoMap @ViewModelKey(WorkoutExerciseListVM::class)
    abstract fun bind4(impl: WorkoutExerciseListVM): ViewModel

    @Binds @IntoMap @ViewModelKey(WorkoutSetConfigVM::class)
    abstract fun bind5(impl: WorkoutSetConfigVM): ViewModel

    @Binds @IntoMap @ViewModelKey(WorkoutPlayerVM::class)
    abstract fun bind6(impl: WorkoutPlayerVM): ViewModel

    @Binds @IntoMap @ViewModelKey(WorkoutExerciseConfigVM::class)
    abstract fun bind7(impl: WorkoutExerciseConfigVM): ViewModel

    @Binds @IntoMap @ViewModelKey(WorkoutExerciseMetricsVM::class)
    abstract fun bind8(impl: WorkoutExerciseMetricsVM): ViewModel

    @Binds @IntoMap @ViewModelKey(WorkoutTimerVM::class)
    abstract fun bind9(impl: WorkoutTimerVM): ViewModel

    @Binds @IntoMap @ViewModelKey(WorkoutResultVM::class)
    abstract fun bind10(impl: WorkoutResultVM): ViewModel
}