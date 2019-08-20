package com.vladislavmyasnikov.core_components.di

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class LocalNavigationModule {

    private val cicerone = Cicerone.create()

    @Provides
    @PerFeature
    fun provideLocalRouter(): Router = cicerone.router

    @Provides
    @PerFeature
    fun provideLocalNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}