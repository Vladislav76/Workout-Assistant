package com.vladislavmyasnikov.workout_library_and_player_impl.di.module

import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.di.annotations.FragmentKey
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.WorkoutCreatorFlow
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_exercise_details.content.WorkoutExerciseCycleListContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_exercise_details.dialog.WorkoutExerciseCycleDialog
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_exercise_details.host.WorkoutExerciseCycleListScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_exercise_list.content.WorkoutExerciseListContent2
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_exercise_list.host.WorkoutExerciseListScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_set_list.content.WorkoutSetListContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_set_list.host.WorkoutSetListScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.WorkoutPlayerFlow
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.content.WorkoutExerciseListConfigContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.content.WorkoutExerciseListContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.content.WorkoutHeaderContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.dialog.WorkoutExerciseDetailsDialog
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.host.WorkoutScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.host.WorkoutSetHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_list.content.WorkoutListContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_list.content.WorkoutListToolbarContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_list.host.WorkoutListScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_player.content.*
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_player.host.WorkoutPlayerScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_result.content.WorkoutResultContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_result.host.WorkoutResultScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.WorkoutLibraryFlow
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContentFragmentBindingModule {

    @Binds @IntoMap @FragmentKey(WorkoutListContent::class)
    abstract fun bind1(impl: WorkoutListContent): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutListToolbarContent::class)
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

    @Binds @IntoMap @FragmentKey(WorkoutSetListContent::class)
    abstract fun bind13(impl: WorkoutSetListContent): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutExerciseListContent2::class)
    abstract fun bind14(impl: WorkoutExerciseListContent2): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutExerciseCycleListContent::class)
    abstract fun bind15(impl: WorkoutExerciseCycleListContent): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutExerciseCycleDialog::class)
    abstract fun bind16(impl: WorkoutExerciseCycleDialog): Fragment
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

    @Binds @IntoMap @FragmentKey(WorkoutSetListScreenHost::class)
    abstract fun bind6(impl: WorkoutSetListScreenHost): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutExerciseListScreenHost::class)
    abstract fun bind7(impl: WorkoutExerciseListScreenHost): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutExerciseCycleListScreenHost::class)
    abstract fun bind8(impl: WorkoutExerciseCycleListScreenHost): Fragment
}

@Module
abstract class FlowFragmentBindingModule {

    @Binds @IntoMap @FragmentKey(WorkoutLibraryFlow::class)
    abstract fun bind1(impl: WorkoutLibraryFlow): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutPlayerFlow::class)
    abstract fun bind2(impl: WorkoutPlayerFlow): Fragment

    @Binds @IntoMap @FragmentKey(WorkoutCreatorFlow::class)
    abstract fun bind3(impl: WorkoutCreatorFlow): Fragment
}