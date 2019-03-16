package com.vladislav.workoutassistant.data.db.dao;

import com.vladislav.workoutassistant.data.db.entity.Workout;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface WorkoutDao {

    @Query("SELECT * FROM workouts WHERE category_id = :categoryId ORDER BY intensity_level_id")
    LiveData<List<Workout>> loadWorkouts(int categoryId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWorkouts(List<Workout> workouts);
}
