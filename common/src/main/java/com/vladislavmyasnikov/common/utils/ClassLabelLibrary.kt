package com.vladislavmyasnikov.common.utils

import kotlin.reflect.KClass

object ClassLabelLibrary {

    private const val NO_LABEL = "NO_LABEL"

    private val labels = mutableMapOf<KClass<*>, String>()

    fun addLabels(labelList: List<Pair<KClass<*>, String>>) {
        for ((key, label) in labelList) {
            labels[key] = label
        }
    }

    fun getLabel(clazz: KClass<*>): String = labels[clazz] ?: NO_LABEL
}