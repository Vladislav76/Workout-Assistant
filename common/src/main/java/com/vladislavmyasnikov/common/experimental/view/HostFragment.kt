package com.vladislavmyasnikov.common.experimental.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import kotlin.reflect.KClass

abstract class HostFragment(@LayoutRes private val viewResource: Int) : BaseFragment(viewResource) {

    override val label = "host_fragment"

    protected abstract val children: List<Pair<Int, Class<out BaseFragment>>>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        for ((containerID, fragmentClass) in children) {
            if (childFragmentManager.findFragmentById(containerID) == null) {
                childFragmentManager.beginTransaction()
                        .add(containerID, childFragmentManager.fragmentFactory.instantiate(fragmentClass.classLoader!!, fragmentClass.name))
                        .commitNow()
            }
        }
    }
}