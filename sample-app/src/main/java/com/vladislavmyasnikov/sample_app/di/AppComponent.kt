package com.vladislavmyasnikov.sample_app.di

import com.vladislavmyasnikov.sample_app.presentation.SampleApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(modules = [ActivitiesModule::class, AndroidInjectionModule::class])
@Singleton
abstract class AppComponent {

    abstract fun inject(app: SampleApp)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun bindSampleApp(application: SampleApp): Builder
    }
}