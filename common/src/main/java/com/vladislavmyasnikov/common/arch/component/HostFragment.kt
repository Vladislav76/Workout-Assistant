package com.vladislavmyasnikov.common.arch.component

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.interfaces.BottomPanelController
import com.vladislavmyasnikov.common.arch.communication.MessageSender
import com.vladislavmyasnikov.common.arch.navigation.OnBackPressedListener
import ru.terrakok.cicerone.Router

abstract class HostFragment(
        @LayoutRes private val viewResource: Int
) : BaseFragment(viewResource), OnBackPressedListener {

    protected abstract val router: Router
    protected abstract val fragmentFactory: FragmentFactory
    protected abstract val children: List<Pair<Int, Class<out BaseFragment>>>
    protected lateinit var bottomPanelController: BottomPanelController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentManager?.fragmentFactory = fragmentFactory
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        if (childFragment is MessageSender) {
            childFragment.defaultReceiver = this
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(activity) {
            if (this !is BottomPanelController) throw RuntimeException("Host activity must be implement 'BottomPanelController' interface")
            bottomPanelController = this
        }

        for ((containerID, childClass) in children) addChild(containerID, childClass)
    }

    override fun onBackPressed(): Boolean {
        var wasProcessed = false
        for (child in childFragmentManager.fragments) {
            if (child is OnBackPressedListener && child.onBackPressed()) wasProcessed = true
        }
        return wasProcessed
    }

    private fun addChild(@IdRes containerID: Int, childClass: Class<out BaseFragment>) {
        if (childFragmentManager.findFragmentByTag(childClass.name) == null) {
            childFragmentManager.beginTransaction()
                    .add(containerID, fragmentFactory.instantiate(childClass.classLoader!!, childClass.name), childClass.name)
                    .commitNow()
        }
    }
}