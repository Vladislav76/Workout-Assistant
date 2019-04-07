package com.vladislav.workoutassistant.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.vladislav.workoutassistant.data.db.entity.SetVsExerciseMatching

@Dao
interface SetVsExerciseMatchingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatching(matching: List<SetVsExerciseMatching>)
}
