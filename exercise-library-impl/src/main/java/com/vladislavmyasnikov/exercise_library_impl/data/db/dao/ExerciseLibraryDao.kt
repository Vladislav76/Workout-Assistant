package com.vladislavmyasnikov.exercise_library_impl.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislavmyasnikov.exercise_library_api.ExerciseInfo
import com.vladislavmyasnikov.exercise_library_impl.data.db.entity.ExerciseEntity
import com.vladislavmyasnikov.exercise_library_impl.data.db.entity.ShortExerciseEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface ExerciseLibraryDao {

    @Query("SELECT id, title, muscle_groups_ids, avatar_id FROM exercise_library")
    fun loadAllExercises(): Observable<List<ShortExerciseEntity>>

    @Query("SELECT * FROM exercise_library WHERE id = :id")
    fun loadExerciseById(id: Long): Single<ExerciseEntity>

    @Query("SELECT id, title, avatar_id FROM exercise_library WHERE id IN (:ids)")
    fun loadExercisesInfo(ids: List<Long>): Single<List<ExerciseInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercises(entity: List<ExerciseEntity>)
}