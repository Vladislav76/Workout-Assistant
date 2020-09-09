package com.vladislavmyasnikov.app.presentation

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vladislavmyasnikov.app.R
import com.vladislavmyasnikov.common.interfaces.BottomPanelController
import com.vladislavmyasnikov.common.arch.navigation.OnBackPressedListener
import com.vladislavmyasnikov.common.arch.navigation.RouterHolder
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity(), RouterHolder, BottomPanelController {

    private companion object {

        const val FLOW_TAG_KEY = "flow_tag"

        enum class FlowTag {
            WORKOUT_LIBRARY, EXERCISE_LIBRARY, WORKOUT_DIARY
        }
    }

    @Inject
    override lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var navigator: Navigator

    private var currentFlowTag: FlowTag? = null

    private val bottomPanelListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem: MenuItem ->
        when (menuItem.itemId) {
            R.id.workouts_tab -> selectTab(FlowTag.WORKOUT_LIBRARY)
            R.id.exercises_tab -> selectTab(FlowTag.EXERCISE_LIBRARY)
            R.id.diary_tab -> selectTab(FlowTag.WORKOUT_DIARY)
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        if (savedInstanceState != null) {
            currentFlowTag = savedInstanceState.getSerializable(FLOW_TAG_KEY) as FlowTag
            supportFragmentManager.fragmentFactory =
                    when (currentFlowTag!!) {
                        FlowTag.WORKOUT_LIBRARY -> Flows.WORKOUT_LIBRARY_LAUNCHER.fragmentFactory
                        FlowTag.EXERCISE_LIBRARY -> Flows.EXERCISE_LIBRARY_LAUNCHER.fragmentFactory
                        FlowTag.WORKOUT_DIARY -> Flows.WORKOUT_DIARY_LAUNCHER.fragmentFactory
                    }
        }
        super.onCreate(savedInstanceState)

        navigator = SupportAppNavigator(this, supportFragmentManager, R.id.content_frame)
        setContentView(R.layout.activity_main)

        Controller.activity = this

        val bottomPanel = findViewById<BottomNavigationView>(R.id.bottom_panel)
        bottomPanel.setOnNavigationItemSelectedListener(bottomPanelListener)

        if (savedInstanceState == null) {
            bottomPanelListener.onNavigationItemSelected(bottomPanel.menu.findItem(R.id.workouts_tab))
//            bottomPanelListener.onNavigationItemSelected(bottomPanel.menu.getItem(0))
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(FLOW_TAG_KEY, currentFlowTag)
    }

    override fun onDestroy() {
        super.onDestroy()
        Controller.activity = null
    }

    override fun hideBottomPanel() {
        bottom_panel.visibility = View.GONE
    }

    override fun showBottomPanel() {
        bottom_panel.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        var currentFragment: Fragment? = null
        for (fragment in fm.fragments) {
            if (fragment.isVisible) {
                currentFragment = fragment
                break
            }
        }
        currentFragment?.let {
            if (it is OnBackPressedListener) {
                val wasProcessed = it.onBackPressed()
                if (wasProcessed) return
            }
            router.exit()
        }
    }

    private fun selectTab(tag: FlowTag) {
        if (currentFlowTag == tag) return

//        val fm = supportFragmentManager
//        val newFragment = fm.findFragmentByTag(tag.name)
//        var currentFragment: Fragment? = null
//
//        for (fragment in fm.fragments) {
//            if (fragment.isVisible) {
//                currentFragment = fragment
//                break
//            }
//        }
//
//        if (currentFragment != null && newFragment != null && currentFragment == newFragment) return
//
//        val transaction = fm.beginTransaction()
//        if (newFragment == null) {
        currentFlowTag = tag

        val newScreen = when (tag) {
            FlowTag.WORKOUT_LIBRARY -> Flows.WORKOUT_LIBRARY_FLOW_SCREEN
            FlowTag.EXERCISE_LIBRARY -> Flows.EXERCISE_LIBRARY_FLOW_SCREEN
            FlowTag.WORKOUT_DIARY -> Flows.WORKOUT_DIARY_FLOW_SCREEN
        }

        router.newRootScreen(newScreen)
//            transaction.add(R.id.content_frame, screen.fragment!!, tag.name)
//        }
//
//        newFragment?.let { transaction.attach(newFragment) }
//        currentFragment?.let { transaction.detach(currentFragment) }
//
//        transaction.commitNow()
    }
}