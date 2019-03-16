package com.vladislav.workoutassistant.data.db.dao;

import com.vladislav.workoutassistant.data.db.entity.DiaryEntry;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DiaryDao {

    @Query("SELECT * FROM diary")
    LiveData<List<DiaryEntry>> loadAllEntries();

    @Query("SELECT * FROM diary WHERE id = :diaryEntryId")
    LiveData<DiaryEntry> loadEntry(int diaryEntryId);

    @Query("DELETE FROM diary WHERE id IN (:ids)")
    void deleteEntriesById(List<Integer> ids);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEntry(DiaryEntry entry);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEntries(List<DiaryEntry> entries);

    @Update
    void updateEntry(DiaryEntry entry);
}
