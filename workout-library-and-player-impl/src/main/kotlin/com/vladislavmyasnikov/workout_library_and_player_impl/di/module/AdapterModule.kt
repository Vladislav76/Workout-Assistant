package com.vladislavmyasnikov.workout_library_and_player_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_list.adapter.WorkoutAdapter
import com.vladislavmyasnikov.workout_library_and_player_impl.presentation.workout_details.adapter.WorkoutExerciseAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides @PerScreen
    fun provide1() = WorkoutAdapter()

    @Provides @PerScreen
    fun provide2() = WorkoutExerciseAdapter()
}