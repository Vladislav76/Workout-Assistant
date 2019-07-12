package com.vladislavmyasnikov.sample_app.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vladislavmyasnikov.core_components.interfaces.FragmentController
import com.vladislavmyasnikov.core_components.interfaces.ScreenTitleController
import com.vladislavmyasnikov.sample_app.R

object Controller : ScreenTitleController, FragmentController {

    var activity: AppCompatActivity? = null

    override fun setDisplayHomeAsUpEnabled(value: Boolean) {
        activity?.supportActionBar?.setDisplayHomeAsUpEnabled(value)
    }

    override fun setTitle(title: CharSequence) {
        activity?.supportActionBar?.title = title
    }

    override fun setTitle(titleId: Int) {
    }

    override fun addFragmentOnTop(fragment: Fragment) {
        activity?.apply {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .addToBackStack(null)
                    .commit()
        }
    }
}