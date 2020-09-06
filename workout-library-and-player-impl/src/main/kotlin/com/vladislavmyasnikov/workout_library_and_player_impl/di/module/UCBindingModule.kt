package com.vladislavmyasnikov.workout_library_and_player_impl.di.module

import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_creation.AccessToWorkoutDataUC
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_creation.ChangeWorkoutStructureUC
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_creation.WorkoutCreationUCImpl
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_details.GetWorkoutUC
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_details.GetWorkoutUCImpl
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_list.GetWorkoutListUC
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_list.GetWorkoutListUCImpl
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_player.*
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_result.GetLastWorkoutResultUC
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_result.GetLastWorkoutResultUCImpl
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_controller.*
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_list.GetWorkoutSetListUC
import com.vladislavmyasnikov.workout_library_and_player_impl.domain.usecase.workout_set_list.GetWorkoutSetListUCImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UCBindingModule {

    @Binds
    abstract fun bind1(impl: GetWorkoutListUCImpl): GetWorkoutListUC

    @Binds
    abstract fun bind2(impl: GetWorkoutUCImpl): GetWorkoutUC

    @Binds
    abstract fun bind3(impl: GetWorkoutSetListUCImpl): GetWorkoutSetListUC

    @Binds
    abstract fun bind4(impl: WorkoutSetControllerUCImpl): GetCurrentWorkoutExerciseListUC

    @Binds
    abstract fun bind5(impl: WorkoutSetControllerUCImpl): ChangeWorkoutSetConfigUC

    @Binds
    abstract fun bind6(impl: WorkoutSetControllerUCImpl): GetCurrentWorkoutSetConfigUC

    @Binds
    abstract fun bind7(impl: WorkoutSetControllerUCImpl): GetWorkoutExerciseUC

    @Binds
    abstract fun bind8(impl: WorkoutSetControllerUCImpl): RequestWorkoutUC

    @Binds
    abstract fun bind9(impl: WorkoutPlayerUCImpl): GetCurrentWorkoutExerciseConfigUC

    @Binds
    abstract fun bind10(impl: WorkoutPlayerUCImpl): GetCurrentWorkoutExerciseUC

    @Binds
    abstract fun bind11(impl: WorkoutPlayerUCImpl): ManageWorkoutProcessUC

    @Binds
    abstract fun bind12(impl: WorkoutPlayerUCImpl): AccessWorkoutExerciseMetricsUC

    @Binds
    abstract fun bind13(impl: WorkoutPlayerUCImpl): GetCurrentWorkoutTimerValueUC

    @Binds
    abstract fun bind14(impl: GetLastWorkoutResultUCImpl): GetLastWorkoutResultUC

    @Binds
    abstract fun bind15(impl: WorkoutCreationUCImpl): AccessToWorkoutDataUC

    @Binds
    abstract fun bind16(impl: WorkoutCreationUCImpl): ChangeWorkoutStructureUC
}