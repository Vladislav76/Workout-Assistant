package com.vladislavmyasnikov.common.experimental.di

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider

class InjectFragmentFactory @Inject constructor(_providers: MutableMap<Class<out Fragment>, Provider<Fragment>>) : FragmentFactory() {

    private val providers: Map<String, Provider<Fragment>> = _providers.mapKeys { (fragmentClass, _) -> fragmentClass.name }

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val provider = providers[className]
        provider?.let { Log.d("InjectFragmentFactory", "provider not null") }
        return providers[className]?.get() ?: super.instantiate(classLoader, className)
    }
}