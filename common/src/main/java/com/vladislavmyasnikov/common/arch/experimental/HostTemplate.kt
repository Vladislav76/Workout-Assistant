package com.vladislavmyasnikov.common.arch.experimental

import com.vladislavmyasnikov.common.arch.fundamental.BaseFragment
import io.reactivex.annotations.Experimental

@Experimental
abstract class HostTemplate {

    abstract val layoutID: Int
    abstract val children: List<Pair<Int, Class<out BaseFragment>>>

    abstract fun handleMessage()
}