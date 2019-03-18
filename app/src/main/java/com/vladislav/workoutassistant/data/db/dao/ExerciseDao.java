package com.vladislav.workoutassistant.data.db.dao;

import com.vladislav.workoutassistant.data.db.entity.Exercise;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM exercises WHERE id IN (:exerciseIds)")
    LiveData<List<Exercise>> loadExercises(List<Integer> exerciseIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExercises(List<Exercise> exercises);
}
