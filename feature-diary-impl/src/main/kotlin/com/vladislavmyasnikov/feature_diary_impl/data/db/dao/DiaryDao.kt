package com.vladislavmyasnikov.feature_diary_impl.data.db.dao

import androidx.room.*
import com.vladislavmyasnikov.feature_diary_impl.data.db.entities.FullDiaryEntry
import com.vladislavmyasnikov.feature_diary_impl.data.db.entities.ShortDiaryEntry
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface DiaryDao {

    @Query("SELECT id, date, start_time, end_time, duration FROM diary")
    fun loadShortEntries(): Observable<List<ShortDiaryEntry>>

    @Query("SELECT * FROM diary WHERE id = :id")
    fun loadEntryById(id: Long): Maybe<FullDiaryEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntry(entry: FullDiaryEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntries(entries: List<FullDiaryEntry>)

    @Update
    fun updateEntry(entry: FullDiaryEntry)

    @Query("DELETE FROM diary WHERE id IN (:ids)")
    fun deleteEntriesByIDs(ids: List<Long>): Completable
}
