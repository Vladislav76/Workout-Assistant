package com.vladislavmyasnikov.workout_library_and_player_impl.di.module

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.annotations.FragmentKey
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.dialog.WorkoutExerciseDetailsDialog
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.content.WorkoutExerciseListConfigContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.content.WorkoutExerciseListContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.content.WorkoutHeaderContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.host.WorkoutScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.host.WorkoutSetHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.content.WorkoutListContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.content.WorkoutListToolbarContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.host.WorkoutListScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.content.*
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.host.WorkoutPlayerScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_result.content.WorkoutResultContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_result.host.WorkoutResultScreenHost
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContentFragmentBindingModule {

    @Binds @IntoMap @FragmentKey(WorkoutListContent::class)
    abstract fun bind1(impl: WorkoutListContent): Fragment

    @Binds @IntoMap  @FragmentKey(WorkoutListToolbarContent::class)
    abstract fun bind2(impl: WorkoutListToolbarContent): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutHeaderContent::class)
    abstract fun bind3(impl: WorkoutHeaderContent): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutExerciseListContent::class)
    abstract fun bind4(impl: WorkoutExerciseListContent): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutExerciseListConfigContent::class)
    abstract fun bind5(impl: WorkoutExerciseListConfigContent): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutExerciseDetailsDialog::class)
    abstract fun bind6(impl: WorkoutExerciseDetailsDialog): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutExerciseConfigContent::class)
    abstract fun bind7(impl: WorkoutExerciseConfigContent): Fragment

    @Binds @IntoMap @FragmentKey(ControlPanelContent::class)
    abstract fun bind8(impl: ControlPanelContent): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutExerciseContent::class)
    abstract fun bind9(impl: WorkoutExerciseContent): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutExerciseMetricsContent::class)
    abstract fun bind10(impl: WorkoutExerciseMetricsContent): Fragment

    @Binds @IntoMap @FragmentKey(TimerContent::class)
    abstract fun bind11(impl: TimerContent): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutResultContent::class)
    abstract fun bind12(impl: WorkoutResultContent): Fragment
}

@Module
abstract class HostFragmentBindingModule {

    @Binds @IntoMap @FragmentKey(WorkoutListScreenHost::class)
    abstract fun bind1(impl: WorkoutListScreenHost): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutSetHost::class)
    abstract fun bind2(impl: WorkoutSetHost): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutScreenHost::class)
    abstract fun bind3(impl: WorkoutScreenHost): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutPlayerScreenHost::class)
    abstract fun bind4(impl: WorkoutPlayerScreenHost): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutResultScreenHost::class)
    abstract fun bind5(impl: WorkoutResultScreenHost): Fragment
}