package com.vladislavmyasnikov.common.arch.component

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.viewmodel.BaseVM
import com.vladislavmyasnikov.common.arch.communication.Event
import io.reactivex.android.schedulers.AndroidSchedulers

abstract class VMDialog <T>(
        @LayoutRes viewResource: Int
) : BaseDialog(viewResource) {

    protected abstract val viewModelFactory: ViewModelProvider.Factory
    protected abstract val viewModel: BaseVM<T, Throwable>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.errors
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { error -> onReceiveError(error) }
                .also { disposables.add(it) }

        viewModel.items
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { item -> onReceiveItem(item) }
                .also { disposables.add(it) }

        viewModel.events
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { event -> onReceiveEvent(event) }
                .also { disposables.add(it) }
    }

    protected open fun onReceiveError(error: Throwable) {
        Toast.makeText(requireActivity(), error.toString(), Toast.LENGTH_SHORT).show()
    }

    protected open fun onReceiveItem(item: T) {}

    protected open fun onReceiveEvent(event: Event) {}
}