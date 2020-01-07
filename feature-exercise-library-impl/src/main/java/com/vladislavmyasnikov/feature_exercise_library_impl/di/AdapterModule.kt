package com.vladislavmyasnikov.feature_exercise_library_impl.di

import android.content.Context
import com.vladislavmyasnikov.common.di.PerScreen
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.adapters.ExerciseAdapter
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.adapters.ExerciseImagePagerAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides
    @PerScreen
    fun provideExerciseAdapter(context: Context) = ExerciseAdapter(context)

    @Provides
    @PerScreen
    fun provideAdapter() = com.vladislavmyasnikov.feature_exercise_library_impl.presentation.experimental.ExerciseAdapter()

    @Provides
    @PerScreen
    fun provideExerciseImagePagerAdapter(context: Context) = ExerciseImagePagerAdapter(context)
}