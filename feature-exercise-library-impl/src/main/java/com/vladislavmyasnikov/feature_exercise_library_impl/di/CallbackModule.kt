package com.vladislavmyasnikov.feature_exercise_library_impl.di

import com.vladislavmyasnikov.common.di.PerScreen
import com.vladislavmyasnikov.common.interfaces.OnItemClickCallback
import dagger.Module
import dagger.Provides

@Module
class CallbackModule {

    @Provides
    @PerScreen
    fun provideExerciseItemClickCallback() = object : OnItemClickCallback {
        override fun onClick(id: Long, title: String) {
        }
    }
}