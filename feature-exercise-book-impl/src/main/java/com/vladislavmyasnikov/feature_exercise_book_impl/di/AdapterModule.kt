package com.vladislavmyasnikov.feature_exercise_book_impl.di

import android.content.Context
import com.vladislavmyasnikov.core_components.di.PerScreen
import com.vladislavmyasnikov.feature_exercise_book_impl.presentation.adapters.ExerciseAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule(private val context: Context) {

    @Provides
    @PerScreen
    fun provideExerciseAdapter(): ExerciseAdapter = ExerciseAdapter(context)
}