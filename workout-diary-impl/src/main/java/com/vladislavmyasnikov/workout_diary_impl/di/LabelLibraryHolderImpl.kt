package com.vladislavmyasnikov.workout_diary_impl.di

import com.vladislavmyasnikov.common.interfaces.LabelLibraryHolder
import com.vladislavmyasnikov.workout_diary_impl.presentation.DiaryFlow
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.content.CompletedWorkoutContent
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.content.CompletedWorkoutToolbarContent
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.host.CompletedWorkoutScreenHost
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.content.CompletedWorkoutListContent
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.content.CompletedWorkoutListToolbarContent
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.host.CompletedWorkoutListScreenHost
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.viewmodel.CompletedWorkoutListVM
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_details.viewmodel.CompletedWorkoutVM
import kotlin.reflect.KClass

object LabelLibraryHolderImpl : LabelLibraryHolder {

    override val labels = listOf<Pair<KClass<*>, String>>(
            // content fragments
            CompletedWorkoutListContent::class to "COMPLETED_WORKOUT_LIST_CONTENT",
            CompletedWorkoutListToolbarContent::class to "COMPLETED_WORKOUT_TOOLBAR_CONTENT",
            CompletedWorkoutContent::class to "COMPLETED_WORKOUT_CONTENT",
            CompletedWorkoutToolbarContent::class to "COMPLETED_WORKOUT_TOOLBAR_CONTENT",

            // host fragments
            CompletedWorkoutListScreenHost::class to "COMPLETED_WORKOUT_LIST_SCREEN_HOST",
            CompletedWorkoutScreenHost::class to "COMPLETED_WORKOUT_DETAILS_SCREEN_HOST",

            // flow fragments
            DiaryFlow::class to "WORKOUT_DIARY_FEATURE_FLOW",

            // dialogs

            // view models
            CompletedWorkoutListVM::class to "COMPLETED_WORKOUT_LIST_VM",
            CompletedWorkoutVM::class to "COMPLETED_WORKOUT_VM"
    )
}