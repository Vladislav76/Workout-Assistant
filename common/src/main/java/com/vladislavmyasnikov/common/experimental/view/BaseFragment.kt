package com.vladislavmyasnikov.common.experimental.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/*
 * Class for debug logging
 */
abstract class BaseFragment(@LayoutRes private val viewResource: Int) : Fragment() {

    open val label = "base_fragment"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(label, "::onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(label, "::onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(viewResource, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(label, "::onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(label, "::onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(label, "::onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(label, "::onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(label, "::onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(label, "::onDestroy")
    }

    protected fun debugMessage(message: String) {
        Log.d(label, message)
    }
}
