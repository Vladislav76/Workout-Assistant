package com.vladislavmyasnikov.feature_diary_impl.domain

import com.vladislavmyasnikov.core_utils.utils.interfaces.Identifiable

data class DiaryEntry(
        val id: Long
) : Identifiable<DiaryEntry> {

    override fun isIdentical(another: DiaryEntry): Boolean = id == another.id
}