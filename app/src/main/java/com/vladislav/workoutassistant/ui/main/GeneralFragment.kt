package com.vladislav.workoutassistant.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import com.vladislav.workoutassistant.ui.main.interfaces.OnFragmentListener

open class GeneralFragment : Fragment() {

    protected var mFragmentListener: OnFragmentListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentListener) {
            mFragmentListener = context
        } else {
            throw IllegalStateException("$context must implement OnFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mFragmentListener = null
    }
}
