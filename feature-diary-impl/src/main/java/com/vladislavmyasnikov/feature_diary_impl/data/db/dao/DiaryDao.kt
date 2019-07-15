package com.vladislavmyasnikov.feature_diary_impl.data.db.dao

import androidx.room.*
import com.vladislavmyasnikov.feature_diary_impl.data.db.entities.FullDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.data.db.entities.ShortDiaryEntry
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface DiaryDao {

    @Query("SELECT id, date, duration FROM diary")
    fun loadShortEntries(): Observable<List<ShortDiaryEntry>> //TODO: change to Maybe in release version, or no???

    @Query("SELECT * FROM diary WHERE id = :id")
    fun loadEntryById(id: Long): Maybe<FullDiaryEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntries(entryFulls: List<FullDiaryEntry>)

    @Update
    fun updateEntries(entryFulls: List<FullDiaryEntry>)

    @Query("DELETE FROM diary WHERE id IN (:ids)")
    fun deleteEntriesById(ids: List<Long>)
}
