package com.vladislav.workoutassistant.ui.main

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vladislav.workoutassistant.R
import com.vladislav.workoutassistant.ui.diary.DiaryFragment
import com.vladislav.workoutassistant.ui.main.interfaces.OnBackButtonListener
import com.vladislav.workoutassistant.ui.main.interfaces.OnFragmentListener
import com.vladislav.workoutassistant.ui.exercises.ExercisesFragment
import com.vladislav.workoutassistant.ui.workouts.WorkoutsFragment

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener, OnFragmentListener {

    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)

        val mainPanel = findViewById<BottomNavigationView>(R.id.main_panel)
        mainPanel.setOnNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            onNavigationItemSelected(mainPanel.menu.getItem(0))
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragmentManager = supportFragmentManager
        val tag = Integer.toString(item.itemId)
        var fragment = fragmentManager.findFragmentByTag(tag)

        if (fragment == null) {
            when (item.itemId) {
                R.id.workouts_tab -> fragment = WorkoutsFragment.newInstance()
                R.id.exercises_tab -> fragment = ExercisesFragment.newInstance(false)
                R.id.diary_tab -> fragment = DiaryFragment.newInstance()
                else -> return false
            }
            fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment!!, tag)
                    .addToBackStack(BACK_STACK_ROOT_TAG)
                    .commit()
            Log.d("MAIN_ACTIVITY", "fragment == null")
        } else {
            Log.d("MAIN_ACTIVITY", "fragment != null")
            fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, 0)
        }

        return true
    }

    override fun addFragmentOnTop(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun updateToolbarTitle(title: CharSequence) {
        mToolbar!!.title = title
    }

    override fun updateToolbarTitle(resId: Int) {
        mToolbar!!.setTitle(resId)
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager

        if (fragmentManager.backStackEntryCount > 1) {
            val fragment = fragmentManager.findFragmentById(R.id.content_frame)
            if (fragment !is OnBackButtonListener || !(fragment as OnBackButtonListener).onBackPressed()) {
                fragmentManager.popBackStackImmediate()
            }
        } else {
            supportFinishAfterTransition()
        }
    }

    companion object {

        private val BACK_STACK_ROOT_TAG = "root_fragment"
    }
}
