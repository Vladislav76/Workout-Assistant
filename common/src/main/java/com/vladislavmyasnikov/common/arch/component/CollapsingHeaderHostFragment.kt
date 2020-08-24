package com.vladislavmyasnikov.common.arch.component

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.vladislavmyasnikov.common.R
import com.vladislavmyasnikov.common.arch.component.HostFragment

abstract class CollapsingHeaderHostFragment : HostFragment(R.layout.template_collapsing_header_and_body) {

    private lateinit var toolbar: CollapsingToolbarLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.collapsing_toolbar)
    }

    fun setTitle(title: CharSequence) {
        toolbar.title = title
    }

    fun setTitle(@StringRes titleRes: Int) {
        toolbar.title = resources.getString(titleRes)
    }
}