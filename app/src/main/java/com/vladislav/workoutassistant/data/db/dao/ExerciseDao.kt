package com.vladislav.workoutassistant.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vladislav.workoutassistant.data.db.entity.Exercise
import com.vladislav.workoutassistant.data.models.ExerciseContent

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises WHERE id = :exerciseId")
    fun loadExercise(exerciseId: Int): LiveData<Exercise>

    @Query("SELECT * FROM exercises WHERE id IN (:exerciseIds)")
    fun loadExercises(exerciseIds: List<Int>): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercises WHERE muscle_group_id == :muscleGroupId")
    fun loadExercises(muscleGroupId: Int): LiveData<List<Exercise>>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM exercises WHERE id IN (:ids)")
    fun loadExercisesById(ids: List<Int>): LiveData<List<ExerciseContent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercises(exercises: List<Exercise>)
}
