package com.vladislavmyasnikov.common.interfaces

import kotlin.reflect.KClass

interface LabelLibraryHolder {

    val labels: List<Pair<KClass<*>, String>>
}