package com.vladislavmyasnikov.sample_app.di

import com.vladislavmyasnikov.sample_app.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, ControllerModule::class])
@Singleton
abstract class AppComponent {

    abstract fun inject(activity: MainActivity)
}