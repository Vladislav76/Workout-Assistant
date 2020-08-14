package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import androidx.lifecycle.ViewModel
import com.vladislavmyasnikov.common.di.annotations.ViewModelKey
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_list.viewmodel.CompletedWorkoutListVM
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_details.viewmodel.CompletedWorkoutVM
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.viewmodel.WorkoutExerciseListVM
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.viewmodel.WorkoutSetConfigVM
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.viewmodel.WorkoutVM
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_list.viewmodel.WorkoutListVM
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.viewmodel.*
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_result.viewmodel.CompletedWorkoutResultVM
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

    @Binds @IntoMap @ViewModelKey(CompletedWorkoutResultVM::class)
    abstract fun bind10(impl: CompletedWorkoutResultVM): ViewModel

    @Binds @IntoMap @ViewModelKey(CompletedWorkoutListVM::class)
    abstract fun bind11(impl: CompletedWorkoutListVM): ViewModel

    @Binds @IntoMap @ViewModelKey(CompletedWorkoutVM::class)
    abstract fun bind12(impl: CompletedWorkoutVM): ViewModel
}