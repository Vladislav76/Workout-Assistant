package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_details.GetWorkoutUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_details.GetWorkoutUCImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_list.GetWorkoutListUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_list.GetWorkoutListUCImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.GetCurrentWorkoutExerciseUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_list.GetWorkoutSetListUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_list.GetWorkoutSetListUCImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UCBindingModule {

    @Binds @PerScreen
    abstract fun bind1(impl: GetWorkoutListUCImpl): GetWorkoutListUC

    @Binds @PerScreen
    abstract fun bind2(impl: GetWorkoutUCImpl): GetWorkoutUC

    @Binds @PerScreen
    abstract fun bind3(impl: GetWorkoutSetListUCImpl): GetWorkoutSetListUC

    @Binds @PerScreen
    abstract fun bind4(impl: WorkoutSetControllerUCImpl): GetCurrentWorkoutExerciseListUC

    @Binds @PerScreen
    abstract fun bind5(impl: WorkoutSetControllerUCImpl): ChangeWorkoutSetConfigUC

    @Binds @PerScreen
    abstract fun bind6(impl: WorkoutSetControllerUCImpl): GetCurrentWorkoutSetConfigUC

    @Binds @PerScreen
    abstract fun bind7(impl: WorkoutSetControllerUCImpl): GetWorkoutExerciseUC

    @Binds @PerScreen
    abstract fun bind8(impl: WorkoutSetControllerUCImpl): RequestWorkoutUC

    @Binds @PerScreen
    abstract fun bind9(impl: WorkoutPlayerUCImpl): GetCurrentWorkoutExerciseConfigUC

    @Binds @PerScreen
    abstract fun bind10(impl: WorkoutPlayerUCImpl): GetCurrentWorkoutExerciseUC

    @Binds @PerScreen
    abstract fun bind11(impl: WorkoutPlayerUCImpl): ManageWorkoutProcessUC

    @Binds @PerScreen
    abstract fun bind12(impl: WorkoutPlayerUCImpl): AccessWorkoutExerciseMetricsUC

    @Binds @PerScreen
    abstract fun bind13(impl: WorkoutPlayerUCImpl): GetCurrentWorkoutTimerValueUC

    @Binds @PerScreen
    abstract fun bind14(impl: WorkoutPlayerUCImpl): SaveWorkoutResultUC
}