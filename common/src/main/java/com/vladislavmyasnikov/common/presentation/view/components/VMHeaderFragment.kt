package com.vladislavmyasnikov.common.presentation.view.components

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.vladislavmyasnikov.common.arch.fundamental.VMFragment

abstract class VMHeaderFragment<T>(@LayoutRes private val viewResource: Int) : VMFragment<T>(viewResource) {

    private lateinit var toolbarFragment: CollapsingHeaderHostFragment

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val parent = parentFragment
        if (parent is CollapsingHeaderHostFragment) toolbarFragment = parent
        else throw RuntimeException("Parent fragment should be an instance of CollapsingHeaderHostFragment class")

    }

    protected fun setTitle(title: CharSequence) {
        toolbarFragment.setTitle(title)
    }

    protected fun setTitle(@StringRes titleRes: Int) {
        toolbarFragment.setTitle(titleRes)
    }
}