package com.vladislavmyasnikov.common.arch.navigation

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import ru.terrakok.cicerone.android.support.SupportAppScreen
import kotlin.reflect.KClass

interface NavigationComponent

abstract class NavigationComponentHolder {

    protected abstract val fragments: Map<KClass<out NavigationComponent>, Class<out Fragment>>
    protected abstract val dialogFragments: Map<KClass<out NavigationComponent>, Class<out DialogFragment>>
    protected abstract val screenInitLambdas: Map<KClass<out NavigationComponent>, (Fragment, NavigationComponent) -> Unit>
    protected abstract val dialogInitLambdas: Map<KClass<out NavigationComponent>, (DialogFragment, NavigationComponent) -> Unit>
    protected abstract val fragmentFactory: FragmentFactory

    private val screens = mutableMapOf<NavigationComponent, SupportAppScreen>()

    fun getScreen(component: NavigationComponent): SupportAppScreen {
        return screens[component] ?: createScreen(component).also { screens[component] = it }
    }

    fun getDialog(component: NavigationComponent, dialogFactory: FragmentFactory): DialogFragment {
        return createDialog(component, dialogFactory)
    }

    private fun createScreen(component: NavigationComponent): SupportAppScreen {
        return object : SupportAppScreen() {
            override fun getFragment(): Fragment? {
                return fragments[component.javaClass.kotlin]?.let { clazz -> fragmentFactory.instantiate(clazz.classLoader!!, clazz.name) }
                        ?.also { screenInitLambdas[component.javaClass.kotlin]?.invoke(it, component) }
            }
        }
    }

    private fun createDialog(component: NavigationComponent, dialogFactory: FragmentFactory): DialogFragment {
        return dialogFragments[component.javaClass.kotlin]?.let { clazz -> dialogFactory.instantiate(clazz.classLoader!!, clazz.name) as DialogFragment }!!
                .also { dialogInitLambdas[component.javaClass.kotlin]?.invoke(it, component) }
    }
}

