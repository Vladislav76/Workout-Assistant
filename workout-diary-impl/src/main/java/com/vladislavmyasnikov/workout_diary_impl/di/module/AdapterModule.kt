package com.vladislavmyasnikov.workout_diary_impl.di.module

import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.workout_diary_impl.presentation.completed_workout_list.adapter.CompletedWorkoutAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides @PerScreen
    fun provide1() = CompletedWorkoutAdapter()
}