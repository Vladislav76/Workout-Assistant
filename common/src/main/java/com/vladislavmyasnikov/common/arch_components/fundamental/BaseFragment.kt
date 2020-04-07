package com.vladislavmyasnikov.common.arch_components.fundamental

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import com.vladislavmyasnikov.common.utils.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/*
 * Class for debug logging
 */
abstract class BaseFragment(@LayoutRes private val viewResource: Int) : Fragment() {

    protected abstract val bus: SharedBus
    protected val disposables = CompositeDisposable()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Logger.debug(this::class, "::onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.debug(this::class, "::onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(viewResource, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.debug(this::class, "::onViewCreated")

        disposables.add(
                bus.packets
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            onReceivePacket(it)
                        }
        )
    }

    override fun onStart() {
        super.onStart()
        Logger.debug(this::class, "::onStart")
    }

    override fun onStop() {
        super.onStop()
        Logger.debug(this::class, "::onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
        Logger.debug(this::class, "::onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Logger.debug(this::class, "::onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.debug(this::class, "::onDestroy")
    }

    protected fun debugMessage(message: String) {
        Logger.debug(this::class, message)
    }

    protected open fun onReceivePacket(packet: Packet) {}
}
