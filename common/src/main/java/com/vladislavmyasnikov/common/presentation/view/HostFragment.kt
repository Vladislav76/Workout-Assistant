package com.vladislavmyasnikov.common.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.interfaces.OnBackPressedListener
import ru.terrakok.cicerone.Router

abstract class HostFragment(@LayoutRes private val viewResource: Int) : BaseFragment(viewResource), OnBackPressedListener {

    protected abstract val router: Router
    protected abstract val fragmentFactory: FragmentFactory
    protected abstract val children: List<Pair<Int, Class<out BaseFragment>>>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentManager?.fragmentFactory = fragmentFactory
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        for ((containerID, fragmentClass) in children) {
            if (childFragmentManager.fragments.size < children.size && childFragmentManager.findFragmentByTag(fragmentClass.name) == null) {
                childFragmentManager.beginTransaction()
                        .add(containerID, fragmentManager!!.fragmentFactory.instantiate(fragmentClass.classLoader!!, fragmentClass.name))
                        .commitNow()
            }
        }
    }

    override fun onBackPressed(): Boolean {
        for (child in childFragmentManager.fragments) {
            debugMessage(child.toString())
            if (child is OnBackPressedListener && child.onBackPressed()) return false
        }
        router.exit()
        return true
    }
}