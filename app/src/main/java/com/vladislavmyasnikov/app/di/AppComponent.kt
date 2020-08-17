package com.vladislavmyasnikov.app.di

import com.vladislavmyasnikov.app.presentation.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(modules = [ActivitiesModule::class, AndroidInjectionModule::class])
@Singleton
abstract class AppComponent {

    abstract fun inject(app: App)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun bindSampleApp(application: App): Builder
    }
}