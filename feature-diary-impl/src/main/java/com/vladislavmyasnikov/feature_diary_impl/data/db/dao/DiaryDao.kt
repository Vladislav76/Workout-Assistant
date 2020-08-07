package com.vladislavmyasnikov.feature_diary_impl.data.db.dao

import androidx.room.*
import com.vladislavmyasnikov.feature_diary_impl.data.db.entity.DiaryEntryEntity
import com.vladislavmyasnikov.feature_diary_impl.data.db.entity.ShortDiaryEntryEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface DiaryDao {

    @Query("SELECT id, date, start_time, end_time, duration FROM diary")
    fun loadShortEntries(): Observable<List<ShortDiaryEntryEntity>>

    @Query("SELECT * FROM diary WHERE id = :id")
    fun loadEntryById(id: Long): Maybe<DiaryEntryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntry(entry: DiaryEntryEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntries(entries: List<DiaryEntryEntity>)

    @Update
    fun updateEntry(entry: DiaryEntryEntity)

    @Query("DELETE FROM diary WHERE id IN (:ids)")
    fun deleteEntriesByIDs(ids: List<Long>): Completable
}
