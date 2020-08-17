package com.vladislavmyasnikov.app.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.vladislavmyasnikov.common.interfaces.ContextHolder

object Controller : ContextHolder {

    var activity: AppCompatActivity? = null

    override fun getContext(): Context = activity!!
}