package com.vladislavmyasnikov.sample_app.di

import android.content.Context
import com.vladislavmyasnikov.sample_app.presentation.SampleApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppContext(): Context = SampleApp.appContext!!
}