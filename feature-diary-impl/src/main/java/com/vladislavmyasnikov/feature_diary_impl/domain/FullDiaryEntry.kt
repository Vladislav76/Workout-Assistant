package com.vladislavmyasnikov.feature_diary_impl.domain

import com.vladislavmyasnikov.core_components.models.TimePoint
import java.sql.Time
import java.util.*

class FullDiaryEntry(
        _id: Long,
        _date: Date,
        _startTime: TimePoint,
        _endTime: TimePoint,
        _duration: TimePoint,
        var description: String
) : ShortDiaryEntry(_id, _date, _startTime, _endTime, _duration)