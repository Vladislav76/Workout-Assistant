package com.vladislavmyasnikov.common.arch.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import ru.terrakok.cicerone.android.support.SupportAppScreen
import kotlin.reflect.KClass

interface Screen

abstract class ScreenHolder {

    protected abstract val fragments: Map<KClass<out Screen>, Class<out Fragment>>
    protected abstract val initLambdas: Map<KClass<out Screen>, (Fragment, Screen) -> Unit>
    protected abstract val fragmentFactory: FragmentFactory

    private val screens = mutableMapOf<Screen, SupportAppScreen>()

    fun getScreen(screen: Screen): SupportAppScreen {
        return screens[screen] ?: createScreen(screen).also { screens[screen] = it }
    }

    private fun createScreen(screen: Screen): SupportAppScreen {
        return object : SupportAppScreen() {
            override fun getFragment(): Fragment? {
                return fragments[screen.javaClass.kotlin]?.let { clazz -> fragmentFactory.instantiate(clazz.classLoader!!, clazz.name) }
                        ?.also { initLambdas[screen.javaClass.kotlin]?.invoke(it, screen) }
            }
        }
    }
}