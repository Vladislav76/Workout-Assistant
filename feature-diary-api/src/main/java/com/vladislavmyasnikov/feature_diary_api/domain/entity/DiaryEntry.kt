package com.vladislavmyasnikov.feature_diary_api.domain.entity

import com.vladislavmyasnikov.common.models.TimePoint
import java.util.*

data class DiaryEntry(
        val id: Long,
        val date: Date,
        val startTime: TimePoint,
        val endTime: TimePoint,
        val duration: TimePoint,
        val description: String,
        val workoutProductivity: Int,
        val workoutId: Long
)