package com.vladislavmyasnikov.sample_app.di

import com.vladislavmyasnikov.common.di.modules.FactoryModule
import com.vladislavmyasnikov.sample_app.presentation.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeActivityAndroidInjector(): MainActivity
}