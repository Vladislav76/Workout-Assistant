package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.api.*
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.impl.GetWorkoutPlanSetsUCImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.impl.GetWorkoutPlanUCImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.impl.GetWorkoutPlansUCImpl
import com.vladislavmyasnikov.feature_workout_library_impl.domain.usecase.impl.ManageCurrentWorkoutSetsUCImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UCBindingModule {

    @Binds @PerFeature
    abstract fun bind1(impl: GetWorkoutPlansUCImpl): GetWorkoutPlansUC

    @Binds @PerFeature
    abstract fun bind2(impl: GetWorkoutPlanUCImpl): GetWorkoutPlanUC

    @Binds @PerFeature
    abstract fun bind3(impl: GetWorkoutPlanSetsUCImpl): GetWorkoutPlanSetsUC

    @Binds @PerFeature
    abstract fun bind4(impl: ManageCurrentWorkoutSetsUCImpl): GetCurrentWorkoutExercisesUC

    @Binds @PerFeature
    abstract fun bind5(impl: ManageCurrentWorkoutSetsUCImpl): ChangeWorkoutSetConfigUC

    @Binds @PerFeature
    abstract fun bind6(impl: ManageCurrentWorkoutSetsUCImpl): GetWorkoutSetConfigUC

    @Binds @PerFeature
    abstract fun bind7(impl: ManageCurrentWorkoutSetsUCImpl): GetWorkoutExerciseUC
}