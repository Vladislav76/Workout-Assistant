package com.vladislavmyasnikov.common.di.modules

import com.vladislavmyasnikov.common.di.annotations.PerFeature
import com.vladislavmyasnikov.common.arch_components.SharedBus
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class LocalNavigationModule {

    private val cicerone = Cicerone.create()

    @Provides @PerFeature
    fun provideLocalRouter(): Router = cicerone.router

    @Provides @PerFeature
    fun provideLocalNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

    @Provides @PerFeature
    fun provideBus() = SharedBus()
}