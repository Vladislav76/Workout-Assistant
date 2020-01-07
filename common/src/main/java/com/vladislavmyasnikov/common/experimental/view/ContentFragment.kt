package com.vladislavmyasnikov.common.experimental.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.vladislavmyasnikov.common.experimental.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

abstract class ContentFragment<T>(@LayoutRes private val viewResource: Int) : BaseFragment(viewResource) {

    override val label = "content_fragment"

    abstract val viewModel: BaseViewModel<T,Throwable>

    private val disposables = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposables.add(
                viewModel.verdicts
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            onReceiveVerdict(it)
                        }
        )
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    protected open fun onReceiveVerdict(verdict: BaseViewModel.RequestState) {
        // verdict receiving
    }

    protected open fun onReceiveError(error: Throwable) {
        Toast.makeText(activity, error.toString(), Toast.LENGTH_SHORT).show()
    }

    protected open fun onReceiveItem(item: T) {
        // item receiving
    }
}