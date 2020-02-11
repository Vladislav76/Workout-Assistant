package com.vladislavmyasnikov.common.arch_components.fundamental

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import com.vladislavmyasnikov.common.arch_components.Packet
import com.vladislavmyasnikov.common.arch_components.SharedBus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

abstract class BaseDialog(@LayoutRes private val viewResource: Int) : DialogFragment() {

    companion object {
        private const val DIALOG_WIDTH_TO_DISPLAY_WIDTH_RATIO = 0.8
    }

    abstract val label: String

    protected abstract val bus: SharedBus
    protected val disposables = CompositeDisposable()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(label, "::onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(label, "::onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(label, "::onActivityCreated")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(viewResource, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(label, "::onViewCreated")

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
        Log.d(label, "::onStart")
    }

    override fun onResume() {
        super.onResume()
        val window = dialog!!.window!!
        val display = window.windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        window.setLayout((size.x * DIALOG_WIDTH_TO_DISPLAY_WIDTH_RATIO).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onStop() {
        super.onStop()
        Log.d(label, "::onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(label, "::onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(label, "::onDestroy")
    }

    protected open fun onReceivePacket(packet: Packet) {}
}