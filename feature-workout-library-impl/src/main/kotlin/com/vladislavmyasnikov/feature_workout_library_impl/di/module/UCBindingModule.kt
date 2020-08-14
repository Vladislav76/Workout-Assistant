package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.completed_workout_list.GetCompletedWorkoutListUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.completed_workout_list.GetCompletedWorkoutListUCImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.completed_workout_details.GetCompletedWorkoutUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.completed_workout_details.GetCompletedWorkoutUCImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_details.GetWorkoutUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_details.GetWorkoutUCImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_list.GetWorkoutListUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_list.GetWorkoutListUCImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.completed_workout_result.GetLastCompletedWorkoutResultUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.completed_workout_result.GetLastCompletedWorkoutResultUCImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_controller.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_list.GetWorkoutSetListUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_set_list.GetWorkoutSetListUCImpl
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
    abstract fun bind14(impl: WorkoutPlayerUCImpl): SaveCompletedWorkoutUC

    @Binds
    abstract fun bind15(impl: GetLastCompletedWorkoutResultUCImpl): GetLastCompletedWorkoutResultUC

    @Binds
    abstract fun bind16(impl: GetCompletedWorkoutListUCImpl): GetCompletedWorkoutListUC

    @Binds
    abstract fun bind17(impl: GetCompletedWorkoutUCImpl): GetCompletedWorkoutUC
}