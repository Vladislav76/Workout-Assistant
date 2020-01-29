package com.vladislavmyasnikov.feature_exercise_library_impl.di

import android.content.Context
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.adapters.ExerciseAdapter
import com.vladislavmyasnikov.feature_exercise_library_impl.presentation.adapters.ExerciseImagePagerAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides @PerScreen
    fun provide1() = ExerciseAdapter()

    @Provides @PerScreen
    fun provide2(context: Context) = ExerciseImagePagerAdapter(context)
}