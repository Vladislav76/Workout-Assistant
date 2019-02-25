package com.vladislav.workoutassistant.data.db.dao;

import com.vladislav.workoutassistant.data.db.entity.ProgramCategoryEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ProgramCategoryDao {

    @Query("SELECT * FROM categories")
    LiveData<List<ProgramCategoryEntity>> loadCategories();

    @Insert
    void insertCategories(List<ProgramCategoryEntity> categories);
}
