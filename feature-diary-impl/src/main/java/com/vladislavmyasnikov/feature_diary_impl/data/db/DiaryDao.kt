package com.vladislavmyasnikov.feature_diary_impl.data.db

import androidx.room.*
import io.reactivex.Maybe

@Dao
interface DiaryDao {

    @Query("SELECT * FROM diary")
    fun loadEntries(): Maybe<List<DiaryEntry>>

    @Query("SELECT * FROM diary WHERE id = :id")
    fun loadEntryById(id: Long): Maybe<DiaryEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntries(entries: List<DiaryEntry>)

    @Update
    fun updateEntries(entries: List<DiaryEntry>)

    @Query("DELETE FROM diary WHERE id IN (:ids)")
    fun deleteEntriesById(ids: List<Long>)
}
