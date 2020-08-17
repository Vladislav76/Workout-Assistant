package com.vladislavmyasnikov.workout_diary_impl.di.module

import com.vladislavmyasnikov.workout_diary_impl.domain.usecase.completed_workout_details.GetCompletedWorkoutUC
import com.vladislavmyasnikov.workout_diary_impl.domain.usecase.completed_workout_details.GetCompletedWorkoutUCImpl
import com.vladislavmyasnikov.workout_diary_impl.domain.usecase.completed_workout_list.GetCompletedWorkoutListUC
import com.vladislavmyasnikov.workout_diary_impl.domain.usecase.completed_workout_list.GetCompletedWorkoutListUCImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UCBindingModule {

    @Binds
    abstract fun bind1(impl: GetCompletedWorkoutListUCImpl): GetCompletedWorkoutListUC

    @Binds
    abstract fun bind2(impl: GetCompletedWorkoutUCImpl): GetCompletedWorkoutUC
}