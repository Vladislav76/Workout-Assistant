package com.vladislavmyasnikov.core_components.interfaces

interface ScreenTitleController {

    fun setDisplayHomeAsUpEnabled(value: Boolean)
    fun setTitle(resId: Int)
    fun setTitle(title: CharSequence)
}