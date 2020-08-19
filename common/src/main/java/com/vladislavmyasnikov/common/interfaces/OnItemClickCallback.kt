package com.vladislavmyasnikov.common.interfaces

fun interface OnItemClickCallback {

    fun onClick(id: Long, title: String)
}