package com.vladislavmyasnikov.sample_app.presentation

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.core_components.interfaces.ContextHolder
import com.vladislavmyasnikov.core_components.interfaces.FragmentController
import com.vladislavmyasnikov.core_components.interfaces.ScreenTitleController
import com.vladislavmyasnikov.core_utils.utils.utils.Logger
import com.vladislavmyasnikov.sample_app.R

object Controller : ScreenTitleController, FragmentController, ContextHolder {

    var activity: AppCompatActivity? = null
    private const val TAG = "APP_CONTROLLER"

    override fun getContext(): Context = activity!!

    override fun setDisplayHomeAsUpEnabled(value: Boolean) {
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(value)
    }

    override fun setTitle(title: CharSequence) {
        activity?.supportActionBar?.title = title
    }

    override fun setTitle(@StringRes resId: Int) {
        activity?.supportActionBar?.setTitle(resId)
    }

    override fun addFragmentOnTop(fragment: Fragment) {
        activity?.apply {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(null)
                    .commit()
            Logger.debug(TAG, "$fragment is on top")
        }
    }
}