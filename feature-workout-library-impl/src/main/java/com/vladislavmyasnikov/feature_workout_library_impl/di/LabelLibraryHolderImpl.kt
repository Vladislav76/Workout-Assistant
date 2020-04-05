package com.vladislavmyasnikov.feature_workout_library_impl.di

import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.WorkoutLibraryFeatureFlow
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.dialogs.WorkoutExerciseDialog
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_details.content.WorkoutExerciseListConfigContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_details.content.WorkoutExerciseListContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_details.content.WorkoutHeaderContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_details.host.WorkoutScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_details.host.WorkoutSetHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.content.WorkoutListContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.content.WorkoutListToolbarContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_list.host.WorkoutListScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_player.content.ControlPanelContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_player.content.ExerciseApproachDataContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_player.content.WorkoutExerciseConfigContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_player.content.WorkoutExerciseContent
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.view.workout_player.host.WorkoutPlayerScreenHost
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.viewmodel.*
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
            ExerciseApproachDataContent::class to "WORKOUT_APPROACH_DATA_CONTENT",
            WorkoutExerciseConfigContent::class to "WORKOUT_EXERCISE_CONFIG_CONTENT",
            WorkoutExerciseContent::class to "WORKOUT_EXERCISE_CONTENT",

            // host fragments
            WorkoutListScreenHost::class to "WORKOUT_LIST_HOST",
            WorkoutScreenHost::class to "WORKOUT_DETAILS_SCREEN_HOST",
            WorkoutSetHost::class to "WORKOUT_SET_HOST",
            WorkoutPlayerScreenHost::class to "WORKOUT_PLAYER_HOST",

            // flow fragments
            WorkoutLibraryFeatureFlow::class to "WORKOUT_LIBRARY_FEATURE_FLOW",

            // dialogs
            WorkoutExerciseDialog::class to "WORKOUT_EXERCISE_DIALOG",

            // view models
            WorkoutListVM::class to "WORKOUT_LIST_VM",
            WorkoutExerciseListVM::class to "WORKOUT_EXERCISE_LIST_VM",
            WorkoutVM::class to "WORKOUT_VM",
            WorkoutExerciseVM::class to "WORKOUT_EXERCISE_VM",
            WorkoutPlayerVM::class to "WORKOUT_PLAYER_VM",
            ExerciseApproachDataVM::class to "WORKOUT_EXERCISE_APPROACH_DATA_VM",
            WorkoutExerciseConfigVM::class to "WORKOUT_EXERCISE_CONFIG_VM",
            WorkoutSetConfigVM::class to "WORKOUT_SET_CONFIG_VM"
    )
}