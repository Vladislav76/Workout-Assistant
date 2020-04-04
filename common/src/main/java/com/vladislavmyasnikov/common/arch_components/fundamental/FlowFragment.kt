package com.vladislavmyasnikov.common.arch_components.fundamental

import android.content.Context
import android.os.Bundle
import com.vladislavmyasnikov.common.R
import com.vladislavmyasnikov.common.interfaces.OnBackPressedListener
import com.vladislavmyasnikov.common.interfaces.RouterHolder
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

abstract class FlowFragment : BaseFragment(R.layout.fragment_container), OnBackPressedListener {

    protected abstract val router: Router
    protected abstract val navigatorHolder: NavigatorHolder
    private lateinit var navigator: Navigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (childFragmentManager.findFragmentById(R.id.container) == null) onStartUp()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed(): Boolean {
        val visibleChildFragment = childFragmentManager.findFragmentById(R.id.container)
        visibleChildFragment?.let {
            if (visibleChildFragment !is OnBackPressedListener || !visibleChildFragment.onBackPressed()) {
                (activity as RouterHolder).router.exit()
            }
        }
        return true
    }

    protected open fun inject() {
        navigator = SupportAppNavigator(requireActivity(), childFragmentManager, R.id.container)
    }

    protected open fun onStartUp() {}
}