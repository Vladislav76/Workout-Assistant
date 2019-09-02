package com.vladislavmyasnikov.feature_workout_library_impl.domain

class FullWorkoutInfo(
        _id: Long,
        _title: String,
        _avatarID: String,
        val description: String,
        val sets: List<SetInfo>
) : ShortWorkoutInfo(_id, _title, _avatarID)