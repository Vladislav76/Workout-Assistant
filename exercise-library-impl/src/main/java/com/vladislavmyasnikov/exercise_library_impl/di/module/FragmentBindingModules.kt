package com.vladislavmyasnikov.exercise_library_impl.di.module

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.annotations.FragmentKey
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_details.content.ExerciseContent
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.content.ExerciseListContent
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.content.ExerciseListToolbarContent
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_details.host.ExerciseScreenHost
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.dialog.ExerciseFilterDialog
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.host.ExerciseListScreenHost
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContentFragmentBindingModule {

    @Binds @IntoMap @FragmentKey(ExerciseListContent::class)
    abstract fun bind1(impl: ExerciseListContent): Fragment

    @Binds @IntoMap @FragmentKey(ExerciseListToolbarContent::class)
    abstract fun bind2(impl: ExerciseListToolbarContent): Fragment

    @Binds @IntoMap @FragmentKey(ExerciseContent::class)
    abstract fun bind3(impl: ExerciseContent): Fragment

    @Binds @IntoMap @FragmentKey(ExerciseFilterDialog::class)
    abstract fun bind4(impl: ExerciseFilterDialog): Fragment
}

@Module
abstract class HostFragmentBindingModule {

    @Binds @IntoMap @FragmentKey(ExerciseListScreenHost::class)
    abstract fun bind1(impl: ExerciseListScreenHost): Fragment

    @Binds @IntoMap @FragmentKey(ExerciseScreenHost::class)
    abstract fun bind2(impl: ExerciseScreenHost): Fragment
}