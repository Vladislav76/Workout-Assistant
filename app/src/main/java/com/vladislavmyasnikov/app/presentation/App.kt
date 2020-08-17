package com.vladislavmyasnikov.app.presentation

import android.app.Application
import com.vladislavmyasnikov.app.di.DaggerAppComponent
import com.vladislavmyasnikov.app.di.FeatureProxyInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        val appComponent = DaggerAppComponent.builder()
                .bindSampleApp(this)
                .build()

        appComponent.inject(this)
        FeatureProxyInjector.prepareFeatures()
    }
}