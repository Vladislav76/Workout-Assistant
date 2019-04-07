package com.vladislav.workoutassistant.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.workoutassistant.data.db.entity.Exercise

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises WHERE id = :exerciseId")
    fun loadExercise(exerciseId: Int): LiveData<Exercise>

    @Query("SELECT * FROM exercises WHERE id IN (:exerciseIds)")
    fun loadExercises(exerciseIds: List<Int>): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE muscle_group_id == :muscleGroupId")
    fun loadExercises(muscleGroupId: Int): LiveData<List<Exercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercises(exercises: List<Exercise>)
}
