package com.vladislavmyasnikov.common.arch.experimental

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.vladislavmyasnikov.common.arch.component.BaseFragment
import io.reactivex.annotations.Experimental

@Experimental
class FragmentHost : Fragment() {

    protected lateinit var fragmentFactory: FragmentFactory

    fun populate(template: HostTemplate) {
        val view = layoutInflater.inflate(template.layoutID, null)
        for ((containerID, childClass) in template.children) addChild(containerID, childClass)
    }

    private fun addChild(@IdRes containerID: Int, childClass: Class<out BaseFragment>) {
        if (childFragmentManager.findFragmentByTag(childClass.name) == null) {
            childFragmentManager.beginTransaction()
                    .add(containerID, fragmentFactory.instantiate(childClass.classLoader!!, childClass.name), childClass.name)
                    .commitNow()
        }
    }
}