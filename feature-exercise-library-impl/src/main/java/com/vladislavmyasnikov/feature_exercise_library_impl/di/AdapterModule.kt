package com.vladislavmyasnikov.feature_exercise_library_impl.di

import android.content.Context
import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.adapters.ExerciseImagePagerAdapter
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.adapters.ExerciseAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides @PerFeature
    fun provideExerciseAdapter() = ExerciseAdapter()

    @Provides @PerFeature
    fun provideExerciseImagePagerAdapter(context: Context) = ExerciseImagePagerAdapter(context)
}