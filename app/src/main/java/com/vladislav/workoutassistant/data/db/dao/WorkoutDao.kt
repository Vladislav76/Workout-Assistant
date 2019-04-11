package com.vladislav.workoutassistant.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vladislav.workoutassistant.data.db.entity.Workout
import com.vladislav.workoutassistant.data.models.WorkoutInfo
import com.vladislav.workoutassistant.data.models.WorkoutProgram

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workouts WHERE category_id = :categoryId ORDER BY intensity_level_id")
    fun loadWorkouts(categoryId: Int): LiveData<List<Workout>>

    @Transaction
    @Query("SELECT * FROM workouts WHERE id = :workoutId")
    fun loadWorkoutProgram(workoutId: Int): LiveData<WorkoutProgram>

    @Transaction
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM workouts WHERE id = :workoutId")
    fun loadWorkoutInfo(workoutId: Int): LiveData<WorkoutInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkouts(workouts: List<Workout>)
}
