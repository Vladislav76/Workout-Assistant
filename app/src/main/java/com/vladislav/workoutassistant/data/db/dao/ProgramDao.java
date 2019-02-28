package com.vladislav.workoutassistant.data.db.dao;

import com.vladislav.workoutassistant.data.db.entity.ProgramEntity;
import com.vladislav.workoutassistant.data.db.entity.SetEntity;
import com.vladislav.workoutassistant.data.model.Program;
import com.vladislav.workoutassistant.data.model.Set;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface ProgramDao {

    @Query("SELECT * FROM programs WHERE category_id = :categoryId")
    LiveData<List<ProgramEntity>> loadProgramsByCategory(int categoryId);

    @Transaction
    @Query("SELECT id, name FROM programs WHERE id = :programId")
    LiveData<Program> loadProgramById(int programId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProgram(ProgramEntity program);
}
