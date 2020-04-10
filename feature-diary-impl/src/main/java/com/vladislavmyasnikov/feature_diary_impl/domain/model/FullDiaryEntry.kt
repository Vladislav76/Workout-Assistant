package com.vladislavmyasnikov.feature_diary_impl.domain.model

import com.vladislavmyasnikov.common.models.TimePoint
import java.util.*

data class FullDiaryEntry(
        val id: Long,
        val date: Date,
        val startTime: TimePoint,
        val endTime: TimePoint,
        val duration: TimePoint,
        val description: String
)