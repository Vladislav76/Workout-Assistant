package com.vladislavmyasnikov.feature_workout_library_impl.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutExerciseEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutSetEntity
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.models.ShortWorkoutEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface WorkoutLibraryDao {

    @Query("SELECT * FROM workout WHERE id = :id")
    fun loadWorkout(id: Long): Single<WorkoutEntity>

    @Query("SELECT * FROM workout_set WHERE id IN (:ids)")
    fun loadWorkoutSetList(ids: List<Long>): Single<List<WorkoutSetEntity>>

    @Query("SELECT * FROM workout_exercise WHERE id IN (:ids)")
    fun loadWorkoutExerciseList(ids: List<Long>): Single<List<WorkoutExerciseEntity>>

    @Query("SELECT id, title, avatar_id FROM workout")
    fun loadShortWorkoutList(): Observable<List<ShortWorkoutEntity>>

    @Insert
    fun insertWorkoutList(list: List<WorkoutEntity>): List<Long>

    @Insert
    fun insertWorkoutSetList(list: List<WorkoutSetEntity>): List<Long>

    @Insert
    fun insertWorkoutExerciseList(list: List<WorkoutExerciseEntity>): List<Long>
}