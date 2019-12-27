package com.vladislavmyasnikov.common.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.common.components.GeneralViewModel
import com.vladislavmyasnikov.common.interfaces.OnBackPressedListener
import com.vladislavmyasnikov.common.interfaces.ScreenTitleController
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Router
import javax.inject.Inject

abstract class GeneralFragment<T : GeneralViewModel<out Any>> : Fragment(), OnBackPressedListener {

    @Inject
    lateinit var screenTitleController: ScreenTitleController

    @Inject
    lateinit var router: Router

    protected lateinit var viewModel: T

    private val disposables = CompositeDisposable()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("GENERAL_FRAG", "onAttach $this")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("GENERAL_FRAG", "onCreate $this")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbar()
        Log.d("GENERAL_FRAG", "onViewCreated $this")

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("GENERAL_FRAG", "onStart $this")
    }

    override fun onResume() {
        super.onResume()
        Log.d("GENERAL_FRAG", "onResume $this")
    }

    override fun onPause() {
        super.onPause()
        Log.d("GENERAL_FRAG", "onPause $this")
    }

    override fun onStop() {
        super.onStop()
        Log.d("GENERAL_FRAG", "onStop $this")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("GENERAL_FRAG", "onDestroyView $this")
        disposables.clear()
    }

    override fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    protected open fun updateToolbar() {
        activity?.invalidateOptionsMenu()
    }

    protected open fun onReceiveLoadingState(value: Boolean) { }

    protected open fun onReceiveError(error: Throwable) {
        Toast.makeText(activity, error.toString(), Toast.LENGTH_SHORT).show()
    }

    protected open fun <T> onReceiveItem(item: T) { }
}