package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.annotations.FragmentKey
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.dialogs.WorkoutExerciseDialog
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_details.content.WorkoutExerciseListContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_details.content.WorkoutHeaderContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_details.content.WorkoutSetConfigContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_details.host.WorkoutScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_details.host.WorkoutSetHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.content.WorkoutListContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.content.WorkoutListToolbarContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.host.WorkoutListScreenHost
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContentFragmentBindingModule {

    @Binds @IntoMap @PerScreen @FragmentKey(WorkoutListContent::class)
    abstract fun bind1(impl: WorkoutListContent): Fragment

    @Binds @IntoMap @PerScreen  @FragmentKey(WorkoutListToolbarContent::class)
    abstract fun bind2(impl: WorkoutListToolbarContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(WorkoutHeaderContent::class)
    abstract fun bind3(impl: WorkoutHeaderContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(WorkoutExerciseListContent::class)
    abstract fun bind4(impl: WorkoutExerciseListContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(WorkoutSetConfigContent::class)
    abstract fun bind5(impl: WorkoutSetConfigContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(WorkoutExerciseDialog::class)
    abstract fun bind6(impl: WorkoutExerciseDialog): Fragment
}

@Module
abstract class HostFragmentBindingModule {

    @Binds @IntoMap @PerFeature @FragmentKey(WorkoutListScreenHost::class)
    abstract fun bind1(impl: WorkoutListScreenHost): Fragment

    @Binds @IntoMap @PerFeature @FragmentKey(WorkoutSetHost::class)
    abstract fun bind2(impl: WorkoutSetHost): Fragment

    @Binds @IntoMap @PerFeature @FragmentKey(WorkoutScreenHost::class)
    abstract fun bind3(impl: WorkoutScreenHost): Fragment
}