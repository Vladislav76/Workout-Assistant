package com.vladislavmyasnikov.sample_app.presentation

import android.app.Application
import android.content.Context
import com.vladislavmyasnikov.sample_app.di.AppComponent
import com.vladislavmyasnikov.sample_app.di.DaggerAppComponent
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class SampleApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = DaggerAppComponent.builder().build()
    }

    companion object {
        lateinit var INSTANCE: SampleApp
    }
}