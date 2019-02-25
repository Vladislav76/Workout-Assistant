package com.vladislav.workoutassistant.data.db.dao;

import com.vladislav.workoutassistant.data.db.entity.ProgramEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ProgramDao {

    @Query("SELECT * FROM programs WHERE category_id = :categoryId")
    LiveData<List<ProgramEntity>> loadProgramsByCategory(int categoryId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProgram(ProgramEntity program);
}
