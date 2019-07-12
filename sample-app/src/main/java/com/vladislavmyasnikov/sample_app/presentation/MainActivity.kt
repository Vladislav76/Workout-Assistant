package com.vladislavmyasnikov.sample_app.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.vladislavmyasnikov.sample_app.R
import com.vladislavmyasnikov.sample_app.di.FeatureProxyInjector

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Controller.activity = this
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        FeatureProxyInjector.getDiaryFeature().diaryLauncher().launch()
    }

    override fun onDestroy() {
        super.onDestroy()
        Controller.activity = null
    }
}