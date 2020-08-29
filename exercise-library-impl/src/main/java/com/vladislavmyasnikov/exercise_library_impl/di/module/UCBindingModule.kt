package com.vladislavmyasnikov.exercise_library_impl.di.module

import com.vladislavmyasnikov.exercise_library_impl.domain.usecase.exercise_details.GetExerciseUC
import com.vladislavmyasnikov.exercise_library_impl.domain.usecase.exercise_details.GetExerciseUCImpl
import com.vladislavmyasnikov.exercise_library_impl.domain.usecase.exercise_list.GetExerciseListUC
import com.vladislavmyasnikov.exercise_library_impl.domain.usecase.exercise_list.GetExerciseListUCImpl
import com.vladislavmyasnikov.exercise_library_impl.domain.usecase.filtered_exercise_list.*
import dagger.Binds
import dagger.Module

@Module
abstract class UCBindingModule {

    @Binds
    abstract fun bind1(impl: GetExerciseListUCImpl): GetExerciseListUC

    @Binds
    abstract fun bind2(impl: GetExerciseUCImpl): GetExerciseUC

    @Binds
    abstract fun bind5(impl: FilterExerciseListUCImpl): GetFilteredExerciseListUC

    @Binds
    abstract fun bind6(impl: FilterExerciseListUCImpl): ManageFiltersUC
}