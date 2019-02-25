package com.vladislav.workoutassistant.data.db.dao;

import com.vladislav.workoutassistant.data.db.entity.SetEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface SetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSets(List<SetEntity> sets);
}
