package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.impl.*
import dagger.Binds
import dagger.Module

@Module
abstract class UCBindingModule {

    @Binds @PerScreen
    abstract fun bind1(impl: GetWorkoutPlansUCImpl): GetWorkoutPlansUC

    @Binds @PerScreen
    abstract fun bind2(impl: GetWorkoutPlanUCImpl): GetWorkoutPlanUC

    @Binds @PerScreen
    abstract fun bind3(impl: GetWorkoutPlanSetsUCImpl): GetWorkoutPlanSetsUC

    @Binds @PerScreen
    abstract fun bind4(impl: ManageCurrentWorkoutSetsUCImpl): GetCurrentWorkoutExercisesUC

    @Binds @PerScreen
    abstract fun bind5(impl: ManageCurrentWorkoutSetsUCImpl): ChangeWorkoutSetConfigUC

    @Binds @PerScreen
    abstract fun bind6(impl: ManageCurrentWorkoutSetsUCImpl): GetWorkoutSetConfigUC

    @Binds @PerScreen
    abstract fun bind7(impl: ManageCurrentWorkoutSetsUCImpl): GetWorkoutExerciseUC

    @Binds @PerScreen
    abstract fun bind8(impl: ManageCurrentWorkoutSetsUCImpl): RequestWorkoutPlanInfoUC

    @Binds @PerScreen
    abstract fun bind9(impl: ManageWorkoutPlayerUCImpl): GetWorkoutExerciseConfigUC

    @Binds @PerScreen
    abstract fun bind10(impl: ManageWorkoutPlayerUCImpl): GetCurrentWorkoutExerciseUC

    @Binds @PerScreen
    abstract fun bind11(impl: ManageWorkoutPlayerUCImpl): ManageExecutingWorkoutUC

    @Binds @PerScreen
    abstract fun bind12(impl: ManageWorkoutPlayerUCImpl): SetCurrentExerciseApproachDataUC

    @Binds @PerScreen
    abstract fun bind13(impl: ManageWorkoutPlayerUCImpl): GetCurrentExerciseApproachDataUC
}