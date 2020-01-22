package com.vladislavmyasnikov.feature_exercise_library_impl.di

import androidx.lifecycle.ViewModel
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.common.di.annotations.ViewModelKey
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ExerciseListViewModel
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ExerciseViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBindingModule {

    @Binds @IntoMap @PerScreen @ViewModelKey(ExerciseListViewModel::class)
    abstract fun bindExerciseListViewModel(impl: ExerciseListViewModel): ViewModel

    @Binds @IntoMap @PerScreen @ViewModelKey(ExerciseViewModel::class)
    abstract fun bindExerciseViewModel(impl: ExerciseViewModel): ViewModel
}