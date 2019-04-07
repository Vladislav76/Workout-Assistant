package com.vladislav.workoutassistant.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vladislav.workoutassistant.data.db.entity.DiaryEntry

@Dao
interface DiaryDao {

    @Query("SELECT * FROM diary")
    fun loadAllEntries(): LiveData<List<DiaryEntry>>

    @Query("SELECT * FROM diary WHERE id = :diaryEntryId")
    fun loadEntry(diaryEntryId: Int): LiveData<DiaryEntry>

    @Query("DELETE FROM diary WHERE id IN (:ids)")
    fun deleteEntriesById(ids: List<Int>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntry(entry: DiaryEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntries(entries: List<DiaryEntry>)

    @Update
    fun updateEntry(entry: DiaryEntry)
}
