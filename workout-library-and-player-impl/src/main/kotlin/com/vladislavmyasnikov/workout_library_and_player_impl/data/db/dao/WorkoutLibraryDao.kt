package com.vladislavmyasnikov.workout_library_and_player_impl.data.db.dao

import androidx.room.*
import com.vladislavmyasnikov.workout_library_and_player_impl.data.db.entity.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface WorkoutLibraryDao {

    @Query("SELECT id, title, avatar_id FROM workout")
    fun loadAllWorkouts(): Observable<List<ShortWorkoutEntity>>

    @Query("SELECT * FROM workout WHERE id = :id")
    fun loadWorkoutById(id: Long): Single<WorkoutEntity>

    @Query("""
        SELECT completed_workout.id, date, duration, workout_productivity, workout.title AS workout_name 
        FROM completed_workout
        JOIN workout ON workout.id = workout_id
        """)
    fun loadAllCompletedWorkouts(): Observable<List<ShortCompletedWorkoutEntity>>

    @Query("""
        SELECT completed_workout.id, date, duration, workout_productivity, start_time, end_time, completed_workout.description, workout_id, workout.title AS workoutName  
        FROM completed_workout
        JOIN workout ON workout.id = workout_id 
        WHERE completed_workout.id = :id
        """)
    fun loadCompletedWorkoutById(id: Long): Single<CompletedWorkoutEntity>

    @Query("SELECT * FROM workout_set WHERE id IN (:ids)")
    fun loadWorkoutSetList(ids: List<Long>): Single<List<WorkoutSetEntity>>

    @Query("SELECT * FROM workout_exercise WHERE id IN (:ids)")
    fun loadWorkoutExerciseList(ids: List<Long>): Single<List<WorkoutExerciseEntity>>

    @Query("SELECT title FROM workout WHERE id IN (:ids)")
    fun loadWorkoutNamesByIds(ids: List<Long>): List<String>

    @Insert
    fun insertWorkoutList(list: List<WorkoutEntity>): List<Long>

    @Insert
    fun insertWorkoutSetList(list: List<WorkoutSetEntity>): List<Long>

    @Insert
    fun insertWorkoutExerciseList(list: List<WorkoutExerciseEntity>): List<Long>

    @Insert
    fun insertCompletedWorkout(entity: CompletedWorkoutEntity): Completable

    @Insert
    fun insertCompletedWorkouts(entities: List<CompletedWorkoutEntity>)
}