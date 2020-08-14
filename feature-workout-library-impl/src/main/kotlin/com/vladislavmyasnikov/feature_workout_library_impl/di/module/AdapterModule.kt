package com.vladislavmyasnikov.feature_workout_library_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.completed_workout_list.adapter.CompletedWorkoutAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_list.adapter.WorkoutAdapter
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.workout_details.adapter.WorkoutExerciseAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides @PerScreen
    fun provide1() = WorkoutAdapter()

    @Provides @PerScreen
    fun provide2() = WorkoutExerciseAdapter()

    @Provides @PerScreen
    fun provide3() = CompletedWorkoutAdapter()
}