package com.vladislavmyasnikov.sample_app.presentation

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.vladislavmyasnikov.common.interfaces.ContextHolder
import com.vladislavmyasnikov.common.legacy.interfaces.ScreenTitleController

object Controller : ScreenTitleController, ContextHolder {

    var activity: AppCompatActivity? = null

    override fun getContext(): Context = activity!!

    override fun setDisplayHomeAsUpEnabled(value: Boolean) {
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(value)
    }

    override fun setTitle(title: CharSequence) {
        activity?.supportActionBar?.title = title
        println()
    }

    override fun setTitle(@StringRes resId: Int) {
        activity?.supportActionBar?.setTitle(resId)
    }
}