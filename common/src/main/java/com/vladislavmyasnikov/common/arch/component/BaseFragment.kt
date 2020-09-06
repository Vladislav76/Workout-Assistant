package com.vladislavmyasnikov.common.arch.component

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.arch.communication.Message
import com.vladislavmyasnikov.common.arch.communication.MessageReceiver
import com.vladislavmyasnikov.common.arch.communication.MessageSender
import com.vladislavmyasnikov.common.utils.Logger
import io.reactivex.disposables.CompositeDisposable

/*
 * Class for debug logging
 */
abstract class BaseFragment(
        @LayoutRes private val viewResource: Int
) : Fragment(), MessageSender, MessageReceiver {

    protected val disposables = CompositeDisposable()

    override var defaultReceiver: MessageReceiver? = null

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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Logger.debug(this::class, "::onActivityCreated")
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

    override fun onDestroy() {
        super.onDestroy()
        Logger.debug(this::class, "::onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Logger.debug(this::class, "::onDetach")
    }

    override fun onReceiveMessage(message: Message, sender: MessageSender) {}

    protected fun debugMessage(message: String) {
        Logger.debug(this::class, message)
    }
}
