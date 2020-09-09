package com.vladislavmyasnikov.app.di

import com.vladislavmyasnikov.app.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributeActivityAndroidInjector(): MainActivity
}