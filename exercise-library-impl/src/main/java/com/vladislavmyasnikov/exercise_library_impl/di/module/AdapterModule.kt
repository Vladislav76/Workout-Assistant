package com.vladislavmyasnikov.exercise_library_impl.di.module

import android.content.Context
import com.vladislavmyasnikov.common.di.annotations.PerScreen
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_list.adapter.ExerciseAdapter
import com.vladislavmyasnikov.exercise_library_impl.presentation.exercise_details.adapter.ExerciseImagePagerAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {

    @Provides @PerScreen
    fun provide1() = ExerciseAdapter()

    @Provides @PerScreen
    fun provide2(context: Context) = ExerciseImagePagerAdapter(context)
}