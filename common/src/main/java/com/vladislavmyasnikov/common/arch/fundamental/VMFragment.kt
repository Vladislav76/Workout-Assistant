package com.vladislavmyasnikov.common.arch.fundamental

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import com.vladislavmyasnikov.common.arch.viewmodel.BaseVM
import com.vladislavmyasnikov.common.arch.Event
import io.reactivex.android.schedulers.AndroidSchedulers

abstract class VMFragment<T>(@LayoutRes private val viewResource: Int) : BaseFragment(viewResource) {

    protected abstract val viewModelFactory: ViewModelProvider.Factory
    protected abstract val viewModel: BaseVM<T, Throwable>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposables.add(
                viewModel.errors
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            onReceiveError(it)
                        }
        )
        disposables.add(
                viewModel.items
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            onReceiveItem(it)
                        }
        )
        disposables.add(
                viewModel.events
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            onReceiveEvent(it)
                        }
        )
    }

    protected open fun onReceiveError(error: Throwable) {
        Toast.makeText(activity, error.toString(), Toast.LENGTH_SHORT).show()
    }

    protected open fun onReceiveItem(item: T) {}

    protected open fun onReceiveEvent(event: Event) {
        debugMessage("::onReceiveEvent")
    }
}