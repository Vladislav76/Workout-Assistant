package com.vladislavmyasnikov.common.experimental.view.components

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import com.vladislavmyasnikov.common.R
import com.vladislavmyasnikov.common.experimental.view.ContentFragment

abstract class ToolbarFragment<T> : ContentFragment<T>(R.layout.toolbar) {

    private lateinit var toolbar: Toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
    }

    protected fun setTitle(title: CharSequence) {
        toolbar.title = title
    }

    protected fun setTitle(@StringRes titleRes: Int) {
        toolbar.setTitle(titleRes)
    }

    protected fun addMenu(@MenuRes menuRes: Int) {
        toolbar.inflateMenu(menuRes)
    }

    protected fun addMenuListener(listener: Toolbar.OnMenuItemClickListener) {
        toolbar.setOnMenuItemClickListener(listener)
    }
}