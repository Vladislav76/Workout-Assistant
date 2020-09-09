package com.vladislavmyasnikov.workout_library_and_player_impl.di

import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.WorkoutLibraryFlow
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_exercise_details.viewmodel.WorkoutExerciseCycleListVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_exercise_details.viewmodel.WorkoutExerciseCycleVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_exercise_list.viewmodel.WorkoutExerciseListVM2
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_creator.workout_set_list.viewmodel.WorkoutSetListVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.dialog.WorkoutExerciseDetailsDialog
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.content.WorkoutExerciseListConfigContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.content.WorkoutExerciseListContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.content.WorkoutHeaderContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.host.WorkoutScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.host.WorkoutSetHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_list.content.WorkoutListContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_list.content.WorkoutListToolbarContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_list.host.WorkoutListScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_player.content.ControlPanelContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_player.content.WorkoutExerciseMetricsContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_player.content.WorkoutExerciseConfigContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_player.content.WorkoutExerciseContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_player.host.WorkoutPlayerScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_player.viewmodel.WorkoutPlayerVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.viewmodel.WorkoutExerciseListVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.viewmodel.WorkoutSetConfigVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_details.viewmodel.WorkoutVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_library.workout_list.viewmodel.WorkoutListVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_player.viewmodel.WorkoutExerciseMetricsVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_player.viewmodel.WorkoutExerciseConfigVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_player.viewmodel.WorkoutExerciseVM
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_result.content.WorkoutResultContent
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_result.host.WorkoutResultScreenHost
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_player.workout_result.viewmodel.WorkoutResultVM
import kotlin.reflect.KClass

object LabelLibraryHolderImpl : LabelLibraryHolder {

    override val labels = listOf<Pair<KClass<*>, String>>(
            // content fragments
            WorkoutListContent::class to "WORKOUT_LIST_CONTENT",
            WorkoutListToolbarContent::class to "WORKOUT_LIST_TOOLBAR_CONTENT",
            WorkoutExerciseListConfigContent::class to "WORKOUT_SET_CONFIG_CONTENT",
            WorkoutExerciseListContent::class to "WORKOUT_EXERCISE_LIST_CONTENT",
            WorkoutHeaderContent::class to "WORKOUT_HEADER_CONTENT",
            ControlPanelContent::class to "CONTROL_PANEL_CONTENT",
            WorkoutExerciseMetricsContent::class to "WORKOUT_APPROACH_DATA_CONTENT",
            WorkoutExerciseConfigContent::class to "WORKOUT_EXERCISE_CONFIG_CONTENT",
            WorkoutExerciseContent::class to "WORKOUT_EXERCISE_CONTENT",
            WorkoutResultContent::class to "WORKOUT_RESULT_CONTENT",

            // host fragments
            WorkoutListScreenHost::class to "WORKOUT_LIST_SCREEN_HOST",
            WorkoutScreenHost::class to "WORKOUT_DETAILS_SCREEN_HOST",
            WorkoutPlayerScreenHost::class to "WORKOUT_PLAYER_SCREEN_HOST",
            WorkoutResultScreenHost::class to "WORKOUT_RESULT_SCREEN_HOST",
            WorkoutSetHost::class to "WORKOUT_SET_HOST",

            // flow fragments
            WorkoutLibraryFlow::class to "WORKOUT_LIBRARY_FLOW",

            // dialogs
            WorkoutExerciseDetailsDialog::class to "WORKOUT_EXERCISE_DIALOG",

            // view models
            WorkoutListVM::class to "WORKOUT_LIST_VM",
            WorkoutExerciseListVM::class to "WORKOUT_EXERCISE_LIST_VM",
            WorkoutVM::class to "WORKOUT_VM",
            WorkoutExerciseVM::class to "WORKOUT_EXERCISE_VM",
            WorkoutPlayerVM::class to "WORKOUT_PLAYER_VM",
            WorkoutExerciseMetricsVM::class to "WORKOUT_EXERCISE_APPROACH_DATA_VM",
            WorkoutExerciseConfigVM::class to "WORKOUT_EXERCISE_CONFIG_VM",
            WorkoutSetConfigVM::class to "WORKOUT_SET_CONFIG_VM",
            WorkoutResultVM::class to "WORKOUT_RESULT_VM",
            WorkoutSetListVM::class to "WORKOUT_SET_LIST_VM",
            WorkoutExerciseListVM2::class to "NEW_WORKOUT_EXERCISE_LIST_VM",
            WorkoutExerciseCycleListVM::class to "WORKOUT_EXERCISE_CYCLE_LIST_VM",
            WorkoutExerciseCycleVM::class to "WORKOUT_EXERCISE_CYCLE_VM"
    )
}