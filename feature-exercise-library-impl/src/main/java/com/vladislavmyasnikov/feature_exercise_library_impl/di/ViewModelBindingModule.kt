package com.vladislavmyasnikov.feature_exercise_library_impl.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.common.di.annotations.ViewModelKey
import com.vladislavmyasnikov.common.experimental.di.ViewModelFactory
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.viewmodels.ExerciseListViewModel
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ExerciseViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBindingModule {

    @Binds @IntoMap @PerFeature @ViewModelKey(ExerciseListViewModel::class)
    abstract fun bindExerciseListViewModel(viewModel: ExerciseListViewModel): ViewModel

    @Binds @IntoMap @PerFeature @ViewModelKey(ExerciseViewModel::class)
    abstract fun bindExerciseViewModel(viewModel: ExerciseViewModel): ViewModel
}