package com.vladislavmyasnikov.features_api.diary

import com.vladislavmyasnikov.common.models.TimePoint

class DiaryEntry(
        val workoutID: Long,
        val duration: TimePoint
)