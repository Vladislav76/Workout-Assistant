package com.vladislavmyasnikov.feature_diary_impl.domain.entity

import com.vladislavmyasnikov.common.models.TimePoint
import com.vladislavmyasnikov.common.interfaces.Identifiable
import java.util.*

data class ShortDiaryEntry(
        override val id: Long,
        val date: Date,
        val duration: TimePoint,
        val workoutProductivity: Int,
        val workoutName: String
) : Identifiable<ShortDiaryEntry> {

    override fun isIdentical(another: ShortDiaryEntry): Boolean = id == another.id
}