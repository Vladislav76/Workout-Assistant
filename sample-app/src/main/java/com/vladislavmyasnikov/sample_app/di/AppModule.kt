package com.vladislavmyasnikov.sample_app.di

import com.vladislavmyasnikov.sample_app.presentation.Controller
import com.vladislavmyasnikov.sample_app.presentation.SampleApp
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import javax.inject.Singleton

@Module
class AppModule {

    private val mainCicerone = Cicerone.create()

    @Provides
    @Singleton
    fun provideAppContext() = SampleApp.INSTANCE.applicationContext!!

    @Provides
    @Singleton
    fun provideController() = Controller

    @Provides
    @Singleton
    fun provideMainRouter() = mainCicerone.router!!

    @Provides
    @Singleton
    fun provideMainNavigatorHolder() = mainCicerone.navigatorHolder!!
}