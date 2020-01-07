package com.vladislavmyasnikov.feature_workout_library_impl.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.SetInfo
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.entities.WorkoutInfo
import com.vladislavmyasnikov.feature_workout_library_impl.data.db.models.ShortWorkoutInfo
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface WorkoutLibraryDao {

    @Query("SELECT * FROM workouts WHERE id = :id")
    fun loadWorkoutByID(id: Long): Single<WorkoutInfo>

    @Query("SELECT * FROM sets WHERE id IN (:ids)")
    fun loadSetsByIDs(ids: List<Long>): Single<List<SetInfo>>

    @Query("SELECT id, title, avatar_id FROM workouts")
    fun loadShortWorkoutsInfo(): Observable<List<ShortWorkoutInfo>>

    @Insert
    fun insertSetsInfo(info: List<SetInfo>): List<Long>

    @Insert
    fun insertWorkoutsInfo(info: List<WorkoutInfo>): List<Long>
}