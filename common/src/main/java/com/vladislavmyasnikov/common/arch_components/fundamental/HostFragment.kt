package com.vladislavmyasnikov.common.arch_components.fundamental

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.interfaces.OnBackPressedListener
import ru.terrakok.cicerone.Router

abstract class HostFragment(@LayoutRes private val viewResource: Int) : BaseFragment(viewResource), OnBackPressedListener {

    override val label: String = "HOST_FRAGMENT_LEGACY_LABEL"

    protected abstract val router: Router
    protected abstract val fragmentFactory: FragmentFactory
    protected abstract val children: List<Pair<Int, Class<out BaseFragment>>>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentManager?.fragmentFactory = fragmentFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        for ((containerID, childClass) in children) addChild(containerID, childClass)
    }

    override fun onBackPressed(): Boolean {
        for (child in childFragmentManager.fragments) {
            if (child is OnBackPressedListener && child.onBackPressed()) return false
        }

        parentFragment.apply {
            return if (this is HostFragment) {
                false
            } else {
                router.exit()
                true
            }
        }
    }

    private fun addChild(@IdRes containerID: Int, childClass: Class<out BaseFragment>) {
        if (childFragmentManager.findFragmentByTag(childClass.name) == null) {
            childFragmentManager.beginTransaction()
                    .add(containerID, fragmentFactory.instantiate(childClass.classLoader!!, childClass.name), childClass.name)
                    .commitNow()
        }
    }
}