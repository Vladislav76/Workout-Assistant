package com.vladislavmyasnikov.common.arch.component

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import ru.terrakok.cicerone.android.support.SupportAppScreen
import java.lang.RuntimeException
import kotlin.reflect.KClass

interface FlowLauncher {

    val fragmentFactory: FragmentFactory
    val flowClass: KClass<out FlowFragment>

    fun createScreen(onFlowCreate: ((Fragment) -> Unit)? = null) = object : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return fragmentFactory.instantiate(
                    flowClass.java.classLoader ?: throw RuntimeException("Flow class hasn't class loader"),
                    flowClass.java.name
            ).also { onFlowCreate?.invoke(it) }
        }
    }
}