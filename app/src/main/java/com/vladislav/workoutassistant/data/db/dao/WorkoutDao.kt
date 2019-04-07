package com.vladislav.workoutassistant.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.workoutassistant.data.db.entity.Workout
import com.vladislav.workoutassistant.data.models.WorkoutProgram

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workouts WHERE category_id = :categoryId ORDER BY intensity_level_id")
    fun loadWorkouts(categoryId: Int): LiveData<List<Workout>>

    @Query("SELECT * FROM workouts WHERE id = :workoutId")
    fun loadWorkoutProgram(workoutId: Int): LiveData<WorkoutProgram>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkouts(workouts: List<Workout>)
}
