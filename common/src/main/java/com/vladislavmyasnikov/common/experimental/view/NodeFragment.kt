package com.vladislavmyasnikov.common.experimental.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout

abstract class NodeFragment(private val children: List<BaseFragment>) : BaseFragment() {

    companion object {
        const val TAG = "node_fragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return LinearLayout(requireContext()).apply {
            tag = TAG
            for (child in children) {
                addView(
                        FrameLayout(requireContext()).apply {
                            tag = child.tag
                        }
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (child in children) {
            childFragmentManager.beginTransaction()
                    .add(child, child.tag)
                    .commit()
        }
    }
}