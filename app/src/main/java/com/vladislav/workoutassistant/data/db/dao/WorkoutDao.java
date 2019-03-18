package com.vladislav.workoutassistant.data.db.dao;

import com.vladislav.workoutassistant.data.db.entity.Workout;
import com.vladislav.workoutassistant.data.model.WorkoutProgram;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface WorkoutDao {

    @Query("SELECT * FROM workouts WHERE category_id = :categoryId ORDER BY intensity_level_id")
    LiveData<List<Workout>> loadWorkouts(int categoryId);

    @Query("SELECT * FROM workouts WHERE id = :workoutId")
    LiveData<WorkoutProgram> loadWorkoutProgram(int workoutId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWorkouts(List<Workout> workouts);
}
