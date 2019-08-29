package com.vladislavmyasnikov.feature_diary_impl.domain

import com.vladislavmyasnikov.common.models.TimePoint
import java.util.*

class FullDiaryEntry(
        _id: Long,
        _date: Date,
        _startTime: TimePoint,
        _endTime: TimePoint,
        _duration: TimePoint,
        var description: String
) : ShortDiaryEntry(_id, _date, _startTime, _endTime, _duration)