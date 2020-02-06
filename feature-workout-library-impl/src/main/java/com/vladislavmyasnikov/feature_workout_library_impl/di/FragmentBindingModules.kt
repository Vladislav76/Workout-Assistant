package com.vladislavmyasnikov.feature_workout_library_impl.di

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.annotations.FragmentKey
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.content.WorkoutContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.content.WorkoutExerciseListContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.content.WorkoutSetContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.host.WorkoutHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout.host.WorkoutSetHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.content.WorkoutListContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.content.WorkoutListToolbarContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.host.WorkoutListHost
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContentFragmentBindingModule {

    @Binds @IntoMap @PerScreen @FragmentKey(WorkoutListContent::class)
    abstract fun bind1(impl: WorkoutListContent): Fragment

    @Binds @IntoMap @PerScreen  @FragmentKey(WorkoutListToolbarContent::class)
    abstract fun bind2(impl: WorkoutListToolbarContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(WorkoutContent::class)
    abstract fun bind3(impl: WorkoutContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(WorkoutExerciseListContent::class)
    abstract fun bind4(impl: WorkoutExerciseListContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(WorkoutSetContent::class)
    abstract fun bind5(impl: WorkoutSetContent): Fragment
}

@Module
abstract class HostFragmentBindingModule {

    @Binds @IntoMap @PerFeature @FragmentKey(WorkoutListHost::class)
    abstract fun bind1(impl: WorkoutListHost): Fragment

    @Binds @IntoMap @PerFeature @FragmentKey(WorkoutHost::class)
    abstract fun bind2(impl: WorkoutHost): Fragment

    @Binds @IntoMap @PerFeature @FragmentKey(WorkoutSetHost::class)
    abstract fun bind3(impl: WorkoutSetHost): Fragment
}