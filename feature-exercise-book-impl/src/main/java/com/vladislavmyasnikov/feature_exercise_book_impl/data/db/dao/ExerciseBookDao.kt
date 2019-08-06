package com.vladislavmyasnikov.feature_exercise_book_impl.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislavmyasnikov.feature_exercise_book_impl.data.db.entities.FullExerciseInfo
import com.vladislavmyasnikov.feature_exercise_book_impl.data.db.entities.ShortExerciseInfo
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface ExerciseBookDao {

    @Query("SELECT id, title FROM exercise_book")
    fun loadShortExercisesInfo(): Single<List<ShortExerciseInfo>>

    @Query("SELECT * FROM exercise_book WHERE id = :id")
    fun loadExerciseInfoById(id: Long): Maybe<FullExerciseInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercisesInfo(info: List<FullExerciseInfo>)
}