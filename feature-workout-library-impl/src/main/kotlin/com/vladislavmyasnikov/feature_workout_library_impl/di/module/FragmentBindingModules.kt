package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.annotations.FragmentKey
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.dialogs.WorkoutExerciseDialog
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.content.WorkoutExerciseListContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.content.WorkoutHeaderContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.content.WorkoutExerciseListConfigContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.host.WorkoutScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.host.WorkoutSetHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_list.content.WorkoutListContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_list.content.WorkoutListToolbarContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_list.host.WorkoutListScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.content.*
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.host.WorkoutPlayerScreenHost
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

    @Binds @IntoMap @PerScreen @FragmentKey(WorkoutExerciseListConfigContent::class)
    abstract fun bind5(impl: WorkoutExerciseListConfigContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(WorkoutExerciseDialog::class)
    abstract fun bind6(impl: WorkoutExerciseDialog): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(WorkoutExerciseConfigContent::class)
    abstract fun bind7(impl: WorkoutExerciseConfigContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(ControlPanelContent::class)
    abstract fun bind8(impl: ControlPanelContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(WorkoutExerciseContent::class)
    abstract fun bind9(impl: WorkoutExerciseContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(WorkoutExerciseMetricsContent::class)
    abstract fun bind10(impl: WorkoutExerciseMetricsContent): Fragment

    @Binds @IntoMap @PerScreen @FragmentKey(TimerContent::class)
    abstract fun bind11(impl: TimerContent): Fragment
}

@Module
abstract class HostFragmentBindingModule {

    @Binds @IntoMap @PerFeature @FragmentKey(WorkoutListScreenHost::class)
    abstract fun bind1(impl: WorkoutListScreenHost): Fragment

    @Binds @IntoMap @PerFeature @FragmentKey(WorkoutSetHost::class)
    abstract fun bind2(impl: WorkoutSetHost): Fragment

    @Binds @IntoMap @PerFeature @FragmentKey(WorkoutScreenHost::class)
    abstract fun bind3(impl: WorkoutScreenHost): Fragment

    @Binds @IntoMap @PerFeature @FragmentKey(WorkoutPlayerScreenHost::class)
    abstract fun bind4(impl: WorkoutPlayerScreenHost): Fragment
}