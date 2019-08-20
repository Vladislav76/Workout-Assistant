package com.vladislavmyasnikov.core_components.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.core_components.R
import com.vladislavmyasnikov.core_components.interfaces.OnBackPressedListener
import com.vladislavmyasnikov.core_components.interfaces.RouterHolder
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

abstract class ContainerFragment : Fragment(), OnBackPressedListener {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    protected lateinit var toolbar: Toolbar

    private lateinit var navigator: Navigator

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d("GENERAL_CONT", "onAttach $this")
        injecting()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        Log.d("GENERAL_CONT", "onViewCreated $this")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (childFragmentManager.findFragmentById(R.id.container) == null) {
            initialLaunch()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("GENERAL_CONT", "onStart $this")
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        Log.d("GENERAL_CONT", "onStop $this")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("GENERAL_CONT", "onDestroyView $this")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("GENERAL_CONT", "onDetach $this")
    }

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.container)
        fragment?.let {
            if (fragment !is OnBackPressedListener || !fragment.onBackPressed()) {
                (activity as RouterHolder).router.exit()
            }
        }
        return true
    }

    open fun injecting() {
        navigator = SupportAppNavigator(activity, childFragmentManager, R.id.container)
    }

    open fun initialLaunch() { }
}