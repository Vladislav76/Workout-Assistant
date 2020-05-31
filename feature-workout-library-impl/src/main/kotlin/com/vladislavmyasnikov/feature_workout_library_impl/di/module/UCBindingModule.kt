package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_details.GetWorkoutUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_details.GetWorkoutUCImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_list.GetWorkoutListUC
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_list.GetWorkoutListUCImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.workout_player.GetWorkoutExerciseUC
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
    abstract fun bind4(impl: WorkoutSetControllerUCImpl): GetWorkoutExerciseListUC

    @Binds @PerScreen
    abstract fun bind5(impl: WorkoutSetControllerUCImpl): ChangeWorkoutSetConfigUC

    @Binds @PerScreen
    abstract fun bind6(impl: WorkoutSetControllerUCImpl): GetWorkoutSetConfigUC

    @Binds @PerScreen
    abstract fun bind7(impl: WorkoutSetControllerUCImpl): GetSelectedWorkoutExerciseUC

    @Binds @PerScreen
    abstract fun bind8(impl: WorkoutSetControllerUCImpl): RequestWorkoutUC

    @Binds @PerScreen
    abstract fun bind9(impl: WorkoutPlayerUCImpl): GetWorkoutExerciseConfigUC

    @Binds @PerScreen
    abstract fun bind10(impl: WorkoutPlayerUCImpl): GetWorkoutExerciseUC

    @Binds @PerScreen
    abstract fun bind11(impl: WorkoutPlayerUCImpl): ChangeWorkoutProcessStateUC

    @Binds @PerScreen
    abstract fun bind12(impl: WorkoutPlayerUCImpl): SetWorkoutExerciseIndicatorsUC

    @Binds @PerScreen
    abstract fun bind13(impl: WorkoutPlayerUCImpl): GetWorkoutExerciseIndicatorsUC
}