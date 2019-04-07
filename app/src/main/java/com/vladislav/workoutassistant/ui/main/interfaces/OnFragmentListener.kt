package com.vladislav.workoutassistant.ui.main.interfaces

import androidx.fragment.app.Fragment

interface OnFragmentListener {

    fun addFragmentOnTop(fragment: Fragment)
    fun updateToolbarTitle(title: CharSequence)
    fun updateToolbarTitle(resId: Int)
}
