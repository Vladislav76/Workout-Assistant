package com.vladislavmyasnikov.feature_diary_impl.domain

import com.vladislavmyasnikov.common.models.TimePoint
import com.vladislavmyasnikov.common.interfaces.Identifiable
import java.util.*

open class ShortDiaryEntry(
        override val id: Long,
        var date: Date,
        var startTime: TimePoint,
        var endTime: TimePoint,
        var duration: TimePoint
) : Identifiable<ShortDiaryEntry> {

    override fun isIdentical(another: ShortDiaryEntry): Boolean = id == another.id
}