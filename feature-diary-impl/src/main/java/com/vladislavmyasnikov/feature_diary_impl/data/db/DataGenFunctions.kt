package com.vladislavmyasnikov.feature_diary_impl.data.db

fun generateEntries(amount: Int): List<DiaryEntry> {
    val entries = mutableListOf<DiaryEntry>()
    for (i in 1..amount) {
        entries.add(DiaryEntry())
    }
    return entries
}
