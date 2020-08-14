package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.annotations.FragmentKey
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_list.content.CompletedWorkoutListContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_list.content.CompletedWorkoutListToolbarContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_list.host.CompletedWorkoutListScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_details.content.CompletedWorkoutContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_details.content.CompletedWorkoutToolbarContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_details.host.CompletedWorkoutScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.dialog.WorkoutExerciseDetailsDialog
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.content.WorkoutExerciseListConfigContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.content.WorkoutExerciseListContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.content.WorkoutHeaderContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.host.WorkoutScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.host.WorkoutSetHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_list.content.WorkoutListContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_list.content.WorkoutListToolbarContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_list.host.WorkoutListScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.content.*
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_player.host.WorkoutPlayerScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_result.content.CompletedWorkoutResultContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_result.host.CompletedWorkoutResultScreenHost
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

    @Binds @IntoMap @FragmentKey(CompletedWorkoutResultContent::class)
    abstract fun bind12(impl: CompletedWorkoutResultContent): Fragment

    @Binds @IntoMap @FragmentKey(CompletedWorkoutListContent::class)
    abstract fun bind13(impl: CompletedWorkoutListContent): Fragment

    @Binds @IntoMap @FragmentKey(CompletedWorkoutListToolbarContent::class)
    abstract fun bind14(impl: CompletedWorkoutListToolbarContent): Fragment

    @Binds @IntoMap @FragmentKey(CompletedWorkoutContent::class)
    abstract fun bind15(impl: CompletedWorkoutContent): Fragment

    @Binds @IntoMap @FragmentKey(CompletedWorkoutToolbarContent::class)
    abstract fun bind16(impl: CompletedWorkoutToolbarContent): Fragment
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

    @Binds @IntoMap @FragmentKey(CompletedWorkoutResultScreenHost::class)
    abstract fun bind5(impl: CompletedWorkoutResultScreenHost): Fragment

    @Binds @IntoMap @FragmentKey(CompletedWorkoutListScreenHost::class)
    abstract fun bind6(impl: CompletedWorkoutListScreenHost): Fragment

    @Binds @IntoMap @FragmentKey(CompletedWorkoutScreenHost::class)
    abstract fun bind7(impl: CompletedWorkoutScreenHost): Fragment
}