package com.vladislavmyasnikov.common.experimental.view

import android.os.Bundle
import androidx.annotation.LayoutRes

abstract class HostFragment(@LayoutRes private val viewResource: Int) : BaseFragment(viewResource) {

    override val label = "host_fragment"

    protected abstract val children: List<Pair<Int,BaseFragment>>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        for (hostPlace in children) {
            if (childFragmentManager.findFragmentById(hostPlace.first) == null) {
                childFragmentManager.beginTransaction()
                        .add(hostPlace.first, hostPlace.second)
                        .commit()
            }
        }
    }
}