package com.vladislavmyasnikov.common.interfaces

interface ScreenTitleController {

    fun setDisplayHomeAsUpEnabled(value: Boolean)
    fun setTitle(resId: Int)
    fun setTitle(title: CharSequence)
}