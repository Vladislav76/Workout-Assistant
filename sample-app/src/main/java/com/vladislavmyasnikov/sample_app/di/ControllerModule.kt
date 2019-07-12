package com.vladislavmyasnikov.sample_app.di

import com.vladislavmyasnikov.sample_app.presentation.Controller
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ControllerModule {

    @Provides
    @Singleton
    fun provideController() = Controller
}