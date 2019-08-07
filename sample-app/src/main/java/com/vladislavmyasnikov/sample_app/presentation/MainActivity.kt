package com.vladislavmyasnikov.sample_app.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.vladislavmyasnikov.core_components.interfaces.OnBackPressedListener
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

        if (savedInstanceState == null) {
//            FeatureProxyInjector.getDiaryFeature().diaryLauncher().launch()
            FeatureProxyInjector.getExerciseBookFeature().exerciseBookLauncher().launch()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Controller.activity = null
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentById(R.id.content_frame)

        if (fragment !is OnBackPressedListener || !fragment.onBackPressed()) {
            if (fragmentManager.backStackEntryCount > 1) {
                fragmentManager.popBackStackImmediate()
            } else {
                supportFinishAfterTransition()
            }
        }
    }

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }
}