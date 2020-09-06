package com.vladislavmyasnikov.common.di.modules

import com.vladislavmyasnikov.common.di.annotations.PerFlow
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class LocalNavigationModule {

    private val cicerone = Cicerone.create()

    @Provides @PerFlow
    fun provideLocalRouter(): Router = cicerone.router

    @Provides @PerFlow
    fun provideLocalNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}