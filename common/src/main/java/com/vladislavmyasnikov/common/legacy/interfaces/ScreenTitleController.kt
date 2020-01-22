package com.vladislavmyasnikov.common.legacy.interfaces

interface ScreenTitleController {

    fun setDisplayHomeAsUpEnabled(value: Boolean)
    fun setTitle(resId: Int)
    fun setTitle(title: CharSequence)
}