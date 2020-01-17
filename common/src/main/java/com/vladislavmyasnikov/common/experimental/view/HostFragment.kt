package com.vladislavmyasnikov.common.experimental.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.vladislavmyasnikov.common.interfaces.OnBackPressedListener
import ru.terrakok.cicerone.Router

abstract class HostFragment(@LayoutRes private val viewResource: Int) : BaseFragment(viewResource), OnBackPressedListener {

    override val label = "host_fragment"

    protected abstract val router: Router
    protected abstract val children: List<Pair<Int, Class<out BaseFragment>>>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        for ((containerID, fragmentClass) in children) {
            if (childFragmentManager.fragments.size < children.size && childFragmentManager.findFragmentByTag(fragmentClass.name) == null) {
                childFragmentManager.beginTransaction()
                        .add(containerID, childFragmentManager.fragmentFactory.instantiate(fragmentClass.classLoader!!, fragmentClass.name))
                        .commitNow()
            }
        }
    }

    override fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}