package com.vladislavmyasnikov.workout_diary_impl.di.module

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.annotations.FragmentKey
import com.vladislavmyasnikov.common.di.annotations.PerFlow
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.workout_diary_impl.presentation.DiaryFlow
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.content.CompletedWorkoutContent
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.content.CompletedWorkoutToolbarContent
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.host.CompletedWorkoutScreenHost
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.content.CompletedWorkoutListContent
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.content.CompletedWorkoutListToolbarContent
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.host.CompletedWorkoutListScreenHost
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContentFragmentBindingModule {

    @Binds @IntoMap @PerScreen @FragmentKey(CompletedWorkoutListContent::class)
    abstract fun bind1(impl: CompletedWorkoutListContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(CompletedWorkoutListToolbarContent::class)
    abstract fun bind2(impl: CompletedWorkoutListToolbarContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(CompletedWorkoutContent::class)
    abstract fun bind3(impl: CompletedWorkoutContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(CompletedWorkoutToolbarContent::class)
    abstract fun bind4(impl: CompletedWorkoutToolbarContent): Fragment
}

@Module
abstract class HostFragmentBindingModule {

    @Binds @IntoMap @PerFlow @FragmentKey(CompletedWorkoutListScreenHost::class)
    abstract fun bind1(impl: CompletedWorkoutListScreenHost): Fragment

    @Binds @IntoMap @PerFlow @FragmentKey(CompletedWorkoutScreenHost::class)
    abstract fun bind2(impl: CompletedWorkoutScreenHost): Fragment
}

@Module
abstract class FlowFragmentBindingModule {

    @Binds @IntoMap @FragmentKey(DiaryFlow::class)
    abstract fun bind1(impl: DiaryFlow): Fragment
}