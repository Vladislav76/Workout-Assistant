package com.vladislavmyasnikov.feature_exercise_library_impl.di

import androidx.lifecycle.ViewModel
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.common.di.annotations.ViewModelKey
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ExerciseListVM
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.viewmodels.ExerciseVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VMBindingModule {

    @Binds @IntoMap @PerScreen @ViewModelKey(ExerciseListVM::class)
    abstract fun bind1(impl: ExerciseListVM): ViewModel

    @Binds @IntoMap @PerScreen @ViewModelKey(ExerciseVM::class)
    abstract fun bind2(impl: ExerciseVM): ViewModel
}