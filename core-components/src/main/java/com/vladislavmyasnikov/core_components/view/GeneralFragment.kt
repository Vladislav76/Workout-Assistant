package com.vladislavmyasnikov.core_components.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.core_components.components.GeneralViewModel
import com.vladislavmyasnikov.core_components.interfaces.FragmentController
import com.vladislavmyasnikov.core_components.interfaces.ScreenTitleController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.logging.Logger
import javax.inject.Inject

open class GeneralFragment<T : GeneralViewModel<out Any>> : Fragment() {

    @Inject
    lateinit var screenTitleController: ScreenTitleController

    @Inject
    lateinit var fragmentController: FragmentController

    lateinit var viewModel: T

    private val disposables = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disposables.add(viewModel.processingState
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    onReceiveLoadingState(it)
                })

        disposables.add(viewModel.errors
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    onReceiveError(it)
                })

        disposables.add(viewModel.items
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    onReceiveItem(it)
                })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }

    protected open fun onReceiveLoadingState(value: Boolean) { }

    protected open fun onReceiveError(error: Throwable) {
        Toast.makeText(activity, error.toString(), Toast.LENGTH_SHORT).show()
    }

    protected open fun <T> onReceiveItem(item: T) { }
}