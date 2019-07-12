package com.vladislavmyasnikov.sample_app.presentation

import android.app.Application
import android.content.Context

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        @Volatile
        var appContext: Context? = null
        private set
    }
}