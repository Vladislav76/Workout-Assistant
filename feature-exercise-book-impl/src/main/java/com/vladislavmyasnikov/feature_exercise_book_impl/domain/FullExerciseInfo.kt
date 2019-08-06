package com.vladislavmyasnikov.feature_exercise_book_impl.domain

class FullExerciseInfo(
        _id: Long,
        _title: String,
        val description: String
) : ShortExerciseInfo(_id, _title)