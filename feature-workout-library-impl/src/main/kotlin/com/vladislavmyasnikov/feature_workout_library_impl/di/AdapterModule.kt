package com.vladislavmyasnikov.feature_workout_library_impl.di

import android.content.Context
import com.vladislavmyasnikov.common.di.PerScreen
import com.vladislavmyasnikov.feature_workout_library_impl.presentation.adapters.WorkoutSetAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides
    @PerScreen
    fun provideWorkoutSetAdapter(context: Context) = WorkoutSetAdapter(context)
}