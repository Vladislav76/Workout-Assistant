package com.vladislavmyasnikov.feature_exercise_library_impl.di

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.annotations.FragmentKey
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.exercise.content.ExerciseContent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.exercise_list.content.ExerciseListContent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.exercise_list.content.ExerciseListToolbarContent
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.exercise.host.ExerciseHost
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.view.exercise_list.host.ExerciseListHost
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContentFragmentBindingModule {

    @Binds @IntoMap @PerScreen @FragmentKey(ExerciseListContent::class)
    abstract fun bind1(impl: ExerciseListContent): Fragment

    @Binds @IntoMap @PerScreen  @FragmentKey(ExerciseListToolbarContent::class)
    abstract fun bind2(impl: ExerciseListToolbarContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(ExerciseContent::class)
    abstract fun bind3(impl: ExerciseContent): Fragment
}

@Module
abstract class HostFragmentBindingModule {

    @Binds @IntoMap @PerFeature @FragmentKey(ExerciseListHost::class)
    abstract fun bind1(impl: ExerciseListHost): Fragment

    @Binds @IntoMap @PerFeature @FragmentKey(ExerciseHost::class)
    abstract fun bind2(impl: ExerciseHost): Fragment
}