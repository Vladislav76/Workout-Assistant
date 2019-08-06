package com.vladislavmyasnikov.feature_exercise_book_impl.domain

import com.vladislavmyasnikov.core_utils.utils.interfaces.Identifiable

open class ShortExerciseInfo(
        val id: Long,
        val title: String
) : Identifiable<ShortExerciseInfo> {

    override fun isIdentical(another: ShortExerciseInfo): Boolean = id == another.id
}