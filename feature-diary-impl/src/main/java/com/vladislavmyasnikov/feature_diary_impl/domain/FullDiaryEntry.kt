package com.vladislavmyasnikov.feature_diary_impl.domain

import java.sql.Time
import java.util.*

class FullDiaryEntry(
        _id: Long,
        _date: Date,
        _duration: Time,
        val startTime: Time,
        val endTime: Time,
        val description: String
) : ShortDiaryEntry(_id, _date, _duration)