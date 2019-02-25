package com.vladislav.workoutassistant.data.db.dao;

import com.vladislav.workoutassistant.data.db.entity.SetAndExerciseMatchingEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface SetAndExerciseMatchingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMatching(List<SetAndExerciseMatchingEntity> matching);
}
