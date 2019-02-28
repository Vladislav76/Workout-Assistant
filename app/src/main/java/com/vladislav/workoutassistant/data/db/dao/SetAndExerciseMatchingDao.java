package com.vladislav.workoutassistant.data.db.dao;

import com.vladislav.workoutassistant.data.db.entity.SetAndExerciseMatchingEntity;
import com.vladislav.workoutassistant.data.model.Exercise;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface SetAndExerciseMatchingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMatching(List<SetAndExerciseMatchingEntity> matching);
}
