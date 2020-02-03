package com.vladislavmyasnikov.common.utils

import com.vladislavmyasnikov.common.interfaces.HasList

class ListHolder<T>(override val list: List<T>) : HasList<T>